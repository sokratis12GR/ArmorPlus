# ArmorPlus rune API

There are two ways to add runes:

- **Datapacks** add runes that use existing effects and abilities. See the [example datapack](example-datapack/README.md).
- **Code** adds new abilities and changes how runes behave, as described below.

The entry points for other mods are in `com.sofodev.armorplus.api`, along with `RuneKind`, `RuneData` and `SocketResult`. They also use these types from other packages:

| Package | Types |
|---|---|
| `com.sofodev.armorplus.registry.rune` | `RuneEffectTypes` |
| `com.sofodev.armorplus.registry.item.extra` | `BuffInstance`, `BuffParser`, `IBuff`, `Buff`, `ToolBuff`, `RangedBuff` |

## Setup

Compile against ArmorPlus through [CurseMaven](https://www.cursemaven.com/), using the project and file ids from its CurseForge page:

```groovy
repositories {
    maven { url = "https://cursemaven.com" }
}

dependencies {
    implementation "curse.maven:armorplus-<project-id>:<file-id>"
}
```

ArmorPlus needs TheDragonLib 1.4.1 or newer at runtime. Declare your dependency on `armorplus` in `neoforge.mods.toml`.

## `ArmorPlusAPI`

Static helpers. Armor and tool effects are separate, so a player's armor effects never include the held tool's effects, or the other way round.

| Method | Description |
|---|---|
| `createRune(kind, entry)` | Creates a rune item, e.g. `createRune(RuneKind.TOOL, "minecraft:haste\|2")` |
| `getRune(stack)` | The `RuneData` of a rune item, or null |
| `resolve(rune)` | The rune's `BuffInstance`, or null if the rune is inactive (runes or the buff disabled, or the buff doesn't exist) |
| `getAllRunes()` | Every rune that can currently be extracted or found in the world, once per buff and kind, at the highest level it's available at. Vanilla effect runes that are only in the creative tab aren't included |
| `getSockets(gear)` | The runes socketed in the equipment |
| `getSocketCapacity(gear)` | The number of sockets, 0 if the item can't hold runes or hasn't rolled its sockets yet |
| `canSocket(gear, rune)` | A `SocketResult`: `OK` or the reason it doesn't fit (see below) |
| `withRune(gear, rune)` | A copy of the equipment (count 1) with the rune socketed |
| `withoutRune(gear, rune)` | A copy of the equipment (count 1) without that one rune; the rune isn't given back |
| `canUpgrade(rune)` | Whether two of this rune can be combined in the ritual: it's active, its level matters and it's below `getMaxRuneLevel` |
| `canUpgradeNative(gear, rune)` | Whether the rune raises the equipment's own effect by a level in the ritual |
| `getNativeLevel(gear, buffId)` | The level of the material's effect on this item, including upgrades; 0 if it doesn't have it |
| `getMaxRuneLevel(buffId)` | The highest level runes and equipment effects of the buff can be upgraded to: `max=` in `runeOverrides`, else `maxRuneLevel` |
| `isExtracted(gear, buffId)` | Whether the buff was extracted from this item in a Rune Altar, so it no longer has it |
| `getUpgraded(rune)` | The rune one level higher |
| `getUpgradeCost(rune)` | The experience level cost to upgrade it, before `RuneUpgradeEvent` |
| `getUpgradeCatalyst(rune)` | The item and count the ritual uses up from the pedestals to upgrade the rune one level, before the events: `upgrade_catalyst@level=` in `runeOverrides`, else `upgrade_catalyst=`, else that level's entry in `ritualCatalysts`, else `ItemStack.EMPTY` |
| `getUpgradeCatalyst(rune, level)` | The same for raising to that level, which is how equipment effects are upgraded |
| `getSocketCatalyst(rune)` | The item and count the ritual uses up from the pedestals to socket the rune, before the events: `socket_catalyst=` in `runeOverrides`, else the `socket` entry in `ritualCatalysts`, else `ItemStack.EMPTY` |
| `getRitualPedestalTag()` | The block tag `armorplus:ritual_pedestals`: what counts as a pedestal of the circle |
| `isRitualActive(level, altar)` | Whether the Rune Altar's circle is complete, which unlocks socketing and upgrading |
| `getRitualPedestalPositions(altar)` | Where the altar's pedestals go, whether they are there or not |
| `getRitualPedestalItems(level, altar)` | The stack on each of those positions, `ItemStack.EMPTY` where there's nothing |
| `hasArmorEffect(player, buff)` | Whether worn sets or armor runes give the buff and it isn't switched off |
| `hasToolEffect(player, buff)` | Whether the runes of the tool in the main hand give the buff and it isn't switched off |
| `hasWeaponEffect(player, buff)` | Whether the item in the main hand gives the buff on hit and it isn't switched off |
| `hasRangedEffect(player, buff)` | Whether the held bow or crossbow gives the buff to its arrows and it isn't switched off |
| `getArmorEffects(player)` | The active armor effects, one per effect at the highest level of all sources |
| `getToolEffects(player)` | The active effects of the runes of the tool in the main hand |
| `getWeaponEffects(player)` | The on-hit effects of the item in the main hand that aren't switched off: its material's effects and weapon runes |
| `getRangedEffects(player)` | The ranged effects of the bow or crossbow in the main hand, otherwise the off hand (its material's and its runes') that aren't switched off |

- `withRune` doesn't check the socketing rules or fire `RuneSocketEvent`, so call `canSocket` first. Likewise, `getUpgraded` doesn't check the upgrading rules or fire `RuneUpgradeEvent`, and `withoutRune` doesn't fire `RuneRemoveEvent`.
- Use the player effect methods on the server. The results are cached, and server events don't refresh the client's cache.
- For the built-in abilities, pass the enum constant to the `has...Effect` methods, e.g. `Buff.FLIGHT`, `ToolBuff.AUTO_SMELT` or `RangedBuff.PIERCING`. For your own abilities, pass the registered instance.
- To check for any effect by its id, including vanilla ones, search the active effects:

```java
boolean hasHaste = ArmorPlusAPI.getArmorEffects(player).stream()
        .anyMatch(effect -> BuffParser.idOf(effect.getBuff()).equals("minecraft:haste"));
```

`canSocket` checks in this order and returns the first problem:

| `SocketResult` | Meaning |
|---|---|
| `NOT_SOCKETABLE` | Runes are disabled, or the item can't hold runes |
| `WRONG_KIND` | The rune's kind doesn't match the equipment, e.g. a tool rune on a sword |
| `WRONG_SLOT` | The rune doesn't fit this armor piece |
| `INACTIVE` | The rune is inactive (see `resolve`) |
| `NO_FREE_SOCKET` | All sockets are full, or the item hasn't rolled its sockets yet |
| `OWN_BUFF` | The equipment already has this buff |
| `ALREADY_SOCKETED` | A rune with this buff is already socketed |
| `OK` | The rune fits |

## The ritual

A Rune Altar with a pedestal 2 blocks away on each of its 4 sides opens the ritual screen, the only place to socket runes and upgrade runes and equipment effects. Other blocks can complete the circle by joining the `armorplus:ritual_pedestals` block tag, but only Empowering Pedestals hold items, one each. Those items can be used up by socketing (its catalyst), by upgrades (the second rune and the upgrade catalyst) and by extraction (the Blank Rune and the catalyst). A catalyst count above 4 can't fit on the pedestals, so it's lowered to 4.

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
{
  "rune_effect.examplemod.lifesteal": "Lifesteal",
  "rune_effect.examplemod.lifesteal.description": "Heals you for part of the damage you deal"
}
```

The description is optional and should fit on one short line. It shows below the ability in item tooltips while the Effect Details key (Left Alt by default) is held, and in the Effects screen. For text that depends on something, like a config value, override `getDescription()`; returning null shows no description.

Then use the id in a datapack rune or a config entry:

```json
{ "kind": "weapon", "effect": "examplemod:lifesteal" }
```

Entries that name an ability for a use it doesn't list in `uses()` are ignored. Custom abilities show up in the Armor, Tool, Weapon and Ranged Effects screens like any other effect, so players can switch them off.

### Hooks

The hooks run on the server only, for players that have the ability and haven't switched it off. `onHit`, `onKill` and `onShoot` also run for mobs using a weapon, bow or crossbow that has the ability, so `attacker` or `shooter` may not be a player. Ranged runes are stored on the arrow when it's fired, so the `RANGED` hooks use the bow or crossbow it was fired from, even if the shooter has switched items since.

| Hook | Use | When |
|---|---|---|
| `onWornTick(player)` | `ARMOR` | Once a second while the armor gives the ability |
| `onDamageTaken(player, event)` | `ARMOR` | Before the player takes damage; change it with `event.setNewDamage(...)` |
| `onHeldTick(player, tool)` | `TOOL` | Once a second while the tool in the main hand gives the ability |
| `onBlockBreak(player, tool, event)` | `TOOL` | When the player breaks a block with the tool; the event can be canceled |
| `onShoot(arrow, weapon, shooter)` | `RANGED` | When an arrow is fired, already in the world and moving. Also runs for extra arrows such as Volley's and Multishot's |
| `onHit(stack, target, attacker)` | `WEAPON`, `TOOL`, `RANGED` | When the weapon or tool hits a mob in melee, or an arrow of the bow or crossbow hits one |
| `onKill(stack, target, attacker)` | `WEAPON`, `TOOL`, `RANGED` | When the weapon or tool kills a mob in melee or an arrow kills one, after `onHit`. Doesn't run if another mod stops the death |

## Events

All events are in `com.sofodev.armorplus.api.event`, are cancelable and fire on `NeoForge.EVENT_BUS`.

| Event | Side | Canceling | Can change |
|---|---|---|---|
| `RuneSocketEvent` | Both | No ritual result, or `/armorplus rune socket` fails | `setCost` (experience levels), `setCatalyst` (`ItemStack.EMPTY` = none); the command ignores both |
| `RuneExtractEvent` | Both | No Rune Altar result for that option | `setCost`, `setCatalyst` (`ItemStack.EMPTY` = none) |
| `RuneRemoveEvent` | Both | The socketed rune can't be taken out in the Rune Altar, and `/armorplus rune unsocket` skips it. The Grindstone doesn't fire it | `setCost` (the command ignores it) |
| `RuneUpgradeEvent` | Both | No ritual result for the two runes | `setCost`, `setCatalyst` (`ItemStack.EMPTY` = none) |
| `NativeEffectUpgradeEvent` | Both | No ritual result for the equipment and rune | `setCost`, `setCatalyst` (`ItemStack.EMPTY` = none) |
| `RuneApplyEvent` | Server | Skips this application of a socketed rune | Nothing |
| `SetEffectApplyEvent` | Server | Skips this application of a set effect or a weapon or bow material effect | Nothing |

- **`RuneExtractEvent`, `RuneRemoveEvent`, `RuneSocketEvent`, `RuneUpgradeEvent` and `NativeEffectUpgradeEvent` fire often**, including while the screen is drawn. Listeners must have no side effects and give the same answer for the same input.
- `RuneSocketEvent` provides `getPlayer()`, which can be null, `getGear()`, `getRune()`, `getCost()` and `getCatalyst()`. `RuneExtractEvent` has the same getters. `RuneRemoveEvent` has them without `getCatalyst()`, and its player is never null. `RuneUpgradeEvent` has `getPlayer()`, which can be null, `getRune()`, `getResult()`, `getCost()` and `getCatalyst()`. `NativeEffectUpgradeEvent` has `getPlayer()`, which can be null, `getGear()`, `getRune()`, `getLevel()` (the level the effect will have), `getCost()` and `getCatalyst()`.
- `RuneApplyEvent` and `SetEffectApplyEvent` extend `EffectApplyEvent`, which provides `getEntity()`, `getEffect()`, `getContext()`, `getStack()` and `getTarget()`. `SetEffectApplyEvent` also has `getMaterial()`, the material's config name.
- `getContext()` is `WORN` (once a second, empty stack), `HELD` (once a second) or `HIT` (a melee or arrow hit: `getEntity()` is the attacker or shooter, which may be a mob, `getTarget()` is the entity hit, and for arrows `getStack()` is the bow or crossbow that fired it). A canceled effect is tried again on the next tick or hit.
- The apply events don't fire for the `onShoot`, `onKill`, `onBlockBreak` and `onDamageTaken` hooks, or for the built-in `RangedBuff` abilities.
- For worn and held instant effects (Instant Health, Saturation, ...) the apply events fire once a second, even while the effect is on cooldown or wouldn't help. A firing event doesn't mean the effect was applied.
