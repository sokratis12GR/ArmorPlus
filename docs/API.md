# ArmorPlus rune API

There are two ways to add runes:

- **Datapacks** add runes that use existing effects and abilities. See the [example datapack](example-datapack/README.md).
- **Code** adds new abilities and changes how runes behave, as described below.

Everything public for other mods is in `com.sofodev.armorplus.api`.

## `ArmorPlusAPI`

Static helpers. Armor and tool effects are separate, so a player's armor effects never include the held tool's effects, or the other way round.

| Method | Description |
|---|---|
| `createRune(kind, entry)` | Creates a rune item, e.g. `createRune(RuneKind.TOOL, "minecraft:haste\|2")` |
| `getRune(stack)` | The `RuneData` of a rune item, or null |
| `resolve(rune)` | The rune's `BuffInstance`, or null if the rune is inactive (runes or the buff disabled, or the buff doesn't exist) |
| `getAllRunes()` | Every rune that can currently be extracted or found in the world, once per buff and kind |
| `getSockets(gear)` | The runes socketed in the equipment |
| `getSocketCapacity(gear)` | The number of sockets, 0 if the item can't hold runes or hasn't rolled its sockets yet |
| `canSocket(gear, rune)` | A `Runes.SocketResult`: `OK` or the reason it doesn't fit |
| `withRune(gear, rune)` | A copy of the equipment (count 1) with the rune socketed |
| `hasArmorEffect(player, buff)` | Whether worn sets or armor runes give the buff and it isn't switched off |
| `hasToolEffect(player, buff)` | Whether the runes of the tool in the main hand give the buff and it isn't switched off |
| `getArmorEffects(player)` | The active armor effects, one per effect at the highest level of all sources |
| `getToolEffects(player)` | The active effects of the runes of the tool in the main hand |

- `withRune` doesn't check the socketing rules or fire `RuneSocketEvent`, so call `canSocket` first.
- Use the player effect methods on the server. The results are cached, and server events don't refresh the client's cache.
- For the built-in abilities, pass the enum constant to `hasArmorEffect` and `hasToolEffect`, e.g. `Buff.FLIGHT` or `ToolBuff.AUTO_SMELT`. For your own abilities, pass the registered instance.

## Custom abilities: `RuneEffectType`

Register an ability in the `armorplus:rune_effect_type` registry. Rune and config entries can then name it by its id. `uses()` is the only method you have to implement.

```java
public class Lifesteal implements RuneEffectType {
    @Override
    public Set<BuffParser.Use> uses() {
        return Set.of(BuffParser.Use.WEAPON);
    }

    @Override
    public void onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.heal(1.0F);
    }
}
```

```java
public static final DeferredRegister<RuneEffectType> EFFECTS =
        DeferredRegister.create(RuneEffectTypes.REGISTRY_KEY, "examplemod");
public static final DeferredHolder<RuneEffectType, Lifesteal> LIFESTEAL = EFFECTS.register("lifesteal", Lifesteal::new);

// in your mod constructor
EFFECTS.register(modBus);
```

Add the display name to your lang file, using the key `rune_effect.<namespace>.<path>`:

```json
{ "rune_effect.examplemod.lifesteal": "Lifesteal" }
```

Then use the id in a datapack rune or a config entry:

```json
{ "kind": "weapon", "effect": "examplemod:lifesteal" }
```

Entries that name an ability for a use it doesn't list in `uses()` are ignored. Custom abilities show up in the Armor, Tool and Weapon Effects screens like any other effect, so players can switch them off.

### Hooks

The hooks run on the server only, for players that have the ability and haven't switched it off.

| Hook | Use | When |
|---|---|---|
| `onWornTick(player)` | `ARMOR` | Once a second while the armor gives the ability |
| `onDamageTaken(player, event)` | `ARMOR` | Before the player takes damage; change it with `event.setNewDamage(...)` |
| `onHeldTick(player, tool)` | `TOOL` | Once a second while the tool in the main hand gives the ability |
| `onBlockBreak(player, tool, event)` | `TOOL` | When the player breaks a block with the tool; the event can be canceled |
| `onHit(stack, target, attacker)` | `WEAPON`, `TOOL` | When the weapon or tool hits a mob in melee |

## Events

All events are in `com.sofodev.armorplus.api.event`, are cancelable and fire on `NeoForge.EVENT_BUS`.

| Event | Side | Canceling | Can change |
|---|---|---|---|
| `RuneSocketEvent` | Both | No anvil output | `setCost` (experience levels) |
| `RuneExtractEvent` | Both | No Rune Altar result for that option | `setCost`, `setCatalyst` (`ItemStack.EMPTY` = none) |
| `RuneApplyEvent` | Server | Skips this application of a socketed rune | Nothing |
| `SetEffectApplyEvent` | Server | Skips this application of a set effect or a weapon material effect | Nothing |

- **`RuneExtractEvent` fires often**, including while the screen is drawn. Listeners must have no side effects and give the same answer for the same input.
- `RuneSocketEvent.getPlayer()` can be null.
- `RuneApplyEvent` and `SetEffectApplyEvent` extend `EffectApplyEvent`, which provides `getEntity()`, `getEffect()`, `getContext()`, `getStack()` and `getTarget()`. `SetEffectApplyEvent` also has `getMaterial()`, the material's config name.
- `getContext()` is `WORN` (once a second, empty stack), `HELD` (once a second) or `HIT` (`getEntity()` is the attacker, which may be a mob, and `getTarget()` is the entity hit). A canceled effect is tried again on the next tick or hit.
- The apply events don't fire for the `onBlockBreak` and `onDamageTaken` hooks.
