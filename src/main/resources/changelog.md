# 1.21.1 - 23.0.0

The rune update: pull the power out of your equipment, socket it into anything you wear or wield, and strengthen it in a ritual. It also brings new materials and gear, effect switches, per-piece armor effects, new commands, a reorganized config and an API for other mods.

**⚠ Before updating:** update TheDragonLib, and note that existing config settings are not carried over (see [Configs](#configs)).

## Dependencies
What you need to install or update alongside this version.

- **TheDragonLib 1.2.1 or newer is required**, so update it alongside ArmorPlus.
- **Optional JEI support:** a Rune Extraction category, a Ritual category with socketing and every step of upgrading, info pages for the circle, and each rune listed separately. The Ritual category shows the circle in formation, with a catalyst on each pedestal the recipe needs. The transfer button fills the open Rune Altar screen: it switches to the right tab and puts catalysts on empty pedestals, never replacing what's on them.

## New & Changed Features
Everything you'll notice in-game, starting with the new materials.

### New Materials & Gear
- **New materials:** Amethyst, Glowstone, Honey, Quartz and Warden armor, weapons, tools, maces and arrows.
  - **Warden:** the Warden drops a **Warden Heart**, the smithing upgrade for the Warden bases (made from Echo Shards and a Sculk Catalyst). Warden gear grants Darkness and Sculk immunity and keeps Wardens calm; Warden tools turn mined sculk into Echo Shards.
  - New abilities: **Illuminate** (Amethyst and Glowstone), **Darkness Immunity**, **Sculk Immunity** and **Warden Pacification**.
  - The Warden Heart is now part of the Slayer Soul, and an Echo Shard part of the Ultimate Material.
- **Shovels** for every material, including Guardian, Super Star, Ender Dragon, Warden and Slayer shovel bases.
- **More tool sets and maces:** Chicken, Slime, Ardite, Cobalt, Knight Slime, Pig Iron, Manyullyn, Frost, Frost Lava, Chainmail, Golden, Copper, Iron, Diamond and Netherite, with the Pig Iron, Cobalt, Ardite, Knight Slime and Manyullyn alloys and Enhanced Copper.
- **New advancements** for the armor and weaponry of every new material.

### Runes
Runes hold a single effect or ability. You get them from your equipment, from the world or by crafting, and they work in the sockets of any armor, weapon or tool, including vanilla and other mods' equipment.

#### Getting Started
The blocks and items you craft first, and what's in the new creative tab.

- **New rune system:** extract the power of ArmorPlus equipment into runes and socket them into other armor, weapons and tools, including vanilla and other mods' equipment. `allowVanillaGear` and `allowModdedGear` switch each group independently, and `excludedRuneMods` leaves out specific mods.
- **Crafting:**
  - **Blank Rune:** 2 per craft, from smooth stone, amethyst shards and lapis.
  - **Rune Altar:** amethyst shards, a Blank Rune, diamonds and obsidian.
  - **Empowering Pedestal:** amethyst shards, a Blank Rune and obsidian.
  - **Socket Punch:** diamond, amethyst shard and obsidian stick.
- New **AP Runes** creative tab with the Rune Altar, Blank Rune, Socket Punch and one rune of every available buff and every vanilla effect, grouped into armor, weapon, tool and ranged runes.

#### Rune Altar & Extraction
The Rune Altar turns the effects of ArmorPlus equipment into runes and takes single runes back out of any equipment.

- **Rune Altar:** place ArmorPlus equipment and a Blank Rune, choose which of its buffs to extract and pay experience levels (`extractionBaseCost`, `extractionCostPerLevel`).
  - Extracted runes are one level weaker than the original buff, never below I (`extractionLevelReduction`).
  - Each effect can be extracted once per item: that item loses the effect for good and 25% of its durability, or 50% if it only has 1-2 extractable effects (`extractionDurabilityCost`, `extractionDurabilityCostFewEffects`, `extractionFewEffects`).
  - The Rune Altar shows the exact durability cost and a dimmed preview of the item afterwards, whose tooltip lists its stats without what's removed. Extracted effects stay on the item's tooltip, struck through.
  - Extractions the item wouldn't survive are greyed out with the reason.
  - A piece that lost a set effect no longer counts toward it, and the effect's rune can be socketed back in.
  - `destroySourceOnExtract = true` destroys the equipment instead and gives back its socketed runes.
  - Strong buffs cost more and need a catalyst: Flight, Warden Pacification, Wither Immunity, Conduit Power and Ender Shot each need a Nether Star.
- **Take out a single rune:** runes socketed in any equipment, including vanilla and other mods' gear, are listed after its buffs. Picking one returns it intact for 5 levels (`runeRemovalXpCost`), with no Blank Rune or catalyst, and the equipment keeps its durability and other runes.

#### The Ritual
Socketing and upgrading happen in a ritual around the Rune Altar, which needs a complete circle of pedestals and a catalyst for each recipe.

- **The ritual:** place 4 Empowering Pedestals 2 blocks away from a Rune Altar, one on each side, to unlock its ritual screen, the only place to socket and upgrade runes. Pedestals hold one item each: socketing and upgrades use their catalyst from them, upgrades can also use the second rune, and extraction its Blank Rune and catalyst.
- **Catalysts:** socketing takes a Lava Crystal and each level of upgrading takes a new material, plus 1 sigil of the rune's type, placed on the pedestals:
  - **Ember Sigil** (level II): 2 per craft, from amethyst shards and an Infused Lava Crystal.
  - **Rime Sigil** (level III): 2 per craft, from amethyst shards and an Infused Frost Crystal.
  - **Equinox Sigil** (level IV): an Ember Sigil, a Rime Sigil and an Infused Frost Lava Crystal.
  - **Astral Sigil** (level V and up): 2 Equinox Sigils, an Ender Dragon Scale, a Guardian Scale and a Wither Bone.
  - **Rune type sigils** (1 on top at every level): 2 per craft, from an Ember Sigil, a Rime Sigil and one item of the type. Each rune uses one: the **Modded Sigil** (Ender Pearl) for buffs of other mods, the **Instant Sigil** (Glistering Melon Slice) for instant effects, the **Effect Sigil** (Fermented Spider Eye) for other effects, and the **Armor** (Iron Chestplate), **Weapon** (Iron Sword), **Tool** (Iron Pickaxe) and **Bow Sigils** (Bow) for abilities.
- **Each recipe can have its own catalyst:** the strongest runes cost a higher sigil to socket: Astral for Flight, Equinox for Warden Pacification, Wither Immunity, Ender Shot, Seeker and Conduit Power, and Rime for Natural Immunity.
  - Change or remove the general ones with the `ritualCatalysts` config, where a rune type sets what its upgrades use on top, e.g. `bow|armorplus:bow_sigil`.
  - Set them per buff in `runeOverrides` with `socket_catalyst=`, `upgrade_catalyst=` and `upgrade_catalyst@level=` (from that level of upgrading on), e.g. `upgrade_catalyst@5=armorplus:astral_sigil*2`.
- **Circle preview:** with an incomplete circle, Show Circle on the Rune Altar draws see-through pedestals where the missing ones go, red if something is in the way. It's only drawn on your screen: no blocks are placed, and it's gone when you complete the circle, walk away, log out or restart.
- **Altar screens:** a new carved look with its own colours and carvings for each page, text that shrinks to fit its space, and pictures of what belongs in empty slots.

#### Upgrading
Stronger runes and stronger equipment effects, both made in the ritual.

- **Rune upgrading:** combine two identical runes in the ritual to make one a level higher, up to level V (`maxRuneLevel`). It costs more at higher levels: 8 levels from I to II, up to 17 from IV to V (`upgradeBaseCost`, `upgradeCostPerLevel`). Abilities and effects whose level makes no difference (`singleLevelEffects`) can't be upgraded.
- **Upgrade limits:** `runeOverrides` entries take `max=level`, the highest level that effect's runes can be upgraded to instead of `maxRuneLevel`.
- **Upgrading equipment effects:** in the ritual, a rune of one of the equipment's own effects, at that effect's level or higher, raises it by a level on that item, up to the same limit. Set effects count at the lowest level among the pieces they need, so upgrade all of them. Extracting an upgraded effect gives a stronger rune and costs 5 more levels per upgrade (`extractionUpgradeCostPerLevel`).

#### Sockets
How equipment gets its sockets, and how runes get in and out of them.

- **Random sockets:** equipment rolls 0-2 sockets when it's crafted or once it's in a player's inventory, including gear from loot, trades or older worlds (50% none, 40% one, 10% two). Smithing upgrades keep the sockets.
- **Socket Punch:** adds one socket to equipment in an Anvil for 10 levels, up to the maximum (`enableSocketPunch`, `socketPunchXpCost`). Needs `randomSockets` enabled.
- **Socketing rules and costs:**
  - A rune can't be socketed if the equipment already has that buff, or the same rune is already socketed.
  - Socketing costs 3 levels by default (`socketXpCost`); strong runes cost more (e.g. Flight costs 25 levels).
- **Removing runes:** grinding equipment in a Grindstone removes its runes and **destroys them by default** (`returnRunesOnRemoval` gives them back); enchantments stay until it's ground again.

#### Armor & Weapon Runes
Runes for what you wear and what you hit with.

- **Armor & Weapon Runes:** socket them into equipment in the ritual. Armor runes work per piece, weapon runes apply their effect on melee hits.
  - When several worn pieces give the same buff, only the highest level counts.
  - Flight from a socketed rune uses the existing flight handling, so it still only removes flight ArmorPlus granted and doesn't conflict with other mods.

#### Tool Runes
Runes for mining tools, which work while the tool is held in your main hand.

- **Tool Runes:** pickaxes, shovels, hoes and other tools (`#minecraft:enchantable/mining`, except axes, which count as weapons) get their own sockets and accept tool runes.
  - Built-in tool runes: **Haste II**, **Night Vision**, **Speed**, **Fire Resistance**, **Water Breathing** and the new **Auto-Smelt** (`armorplus:auto_smelt`: mined blocks drop their smelted result, using the `smeltingRecipes` list of the Infused Lava tools first and furnace recipes for everything else; Fortune increases the drops, and block experience and other mods' block protection all work). They are always available in the AP Runes tab, JEI and world loot; `tool|...` entries in `worldRunes` add more.
  - Tool effects are separate from armor effects: tool abilities like Auto-Smelt can't be used as set effects, and set abilities like Flight can't be put on tool runes.
  - Tool runes can also carry on-hit debuffs like weapon runes (`minecraft:wither`, `minecraft:poison`, `minecraft:slowness`, ..., and `armorplus:ignite`): they're applied to the mobs you hit with the tool, not to you.
  - ArmorPlus pickaxes and shovels count as tools: they take tool runes, while extracting from them in the Rune Altar gives weapon runes of their on-hit effects. Axes, swords, battle axes and maces count as weapons.

#### Ranged Runes & Bows
Runes for bows and crossbows, and the effects ArmorPlus bows now have of their own.

- **Ranged Runes:** bows and crossbows, including vanilla and other mods' ones, get their own sockets and accept ranged runes. The runes are stored on each arrow when it's fired, so switching weapons mid-flight changes nothing, and they stack with the arrow's own effect.
  - **Piercing:** arrows pass through one more mob per level.
  - **Volley:** fires 1 extra arrow per side per level, up to level III (6 extra arrows), that can't be picked up.
  - **Ricochet:** arrows bounce to a nearby hostile mob at half damage, one more bounce per level.
  - **Long Shot:** more damage the farther the target, rising faster and with a higher limit per level.
  - **Marked:** the target glows and takes more damage from your next arrow, more per level. That hit uses up the mark, and the arrow after it can mark again.
  - **Quick Draw:** draws or loads faster, more per level, up to level IV.
  - **Ammo Saver:** arrows that hit a mob return to your inventory, and a 20% chance per level not to use an arrow when firing (up to 80% at level IV).
  - **Seeker:** arrows lock onto hostile mobs ahead of them and curve to hit them. Rare: crafted from a Blank Rune, Spyglass and Echo Shard, or found in Ancient Cities.
  - **Ender Shot:** teleports you to where the arrow lands, with a 30 second cooldown and 10 durability per shot (`enderShotCooldown`, `enderShotDurability`). Rare: crafted from a Blank Rune, Eye of Ender, Shulker Shell and Nether Star, or found in End Cities.
  - Ranged runes can also carry on-hit effects like weapon runes (`minecraft:poison`, `minecraft:slowness`, `minecraft:wither`, ...), applied to the mobs the arrows hit. Levels work with rune upgrading.
- **ArmorPlus bows have material effects:** a bow's arrows now apply the `weaponBuffs` of its material, shown in its tooltip and the Ranged Effects screen, and they can be extracted as ranged runes in the Rune Altar. They need `enableRunes`, and `enableWeaponEffects` switches them off together with the material's weapons.
- **Some bows have special abilities:** a new `bowBuffs` list per material holds what only its bows get. The Guardian bow has Piercing, the Super Star bow Volley, the Ender Dragon bow Ender Shot, the Warden bow Seeker, and the Slayer bow all four. They can be extracted like other bow effects; Ender Shot needs a Nether Star (`runeOverrides`).
- **ArmorPlus arrows work with bow enchantments:** Power, Punch, Flame, Piercing and other effects of the bow or crossbow now apply to them, and arrows fired with Infinity or in creative can no longer be picked up.
- **ArmorPlus bows now add their damage bonus:** the bonus used to replace the arrow's base damage. It's now added on top, and ArmorPlus arrows deal their own damage from any bow.
- **Clearer arrow and weapon effects:** Redstone arrows now apply Mining Fatigue and Emerald arrows Slowness, matching their bows. Infused Lava arrows set targets on fire for 5 seconds instead of a fraction of a second, and their tooltip shows their real damage. Guardian, Warden and Slayer weapons and bows no longer apply Nausea, and Slayer no longer applies Darkness.

#### Finding & Adding Runes
Where runes come from besides your own equipment, and how datapacks add more.

- **World runes:** runes of most vanilla effects can be found in chests throughout the world, from dungeons, mineshafts, temples and shipwrecks to Ancient Cities and End Cities (see `runeLootSources`). Only Instant Health, Instant Damage, Saturation and the omens aren't found in the world.
- **Datapack runes:** runes can be added with datapacks at `data/<namespace>/armorplus/rune/<name>.json`, e.g. `{ "kind": "tool", "effect": "minecraft:haste", "level": 2 }`. They are synced to clients and show up in the AP Runes tab, JEI and world loot.
  - Optional fields: `seconds` (on-hit duration), `slots` (armor pieces, e.g. `["head"]`), `socket_cost`, `extraction` (`cost`, `catalyst` `{ "item", "count" }`, `extractable`) and `loot` (`[{ "table", "chance" }]`, rolled on its own; runes without `loot` use the `runeLootSources` tables).
  - The built-in tool runes and world runes are datapack files too, so datapacks can replace them. The `worldRunes` config is empty by default and only adds extra runes; `runeOverrides` wins over the datapack costs.

#### Icons & Tooltips
How runes, sockets and abilities show up in your inventory.

- **Level numerals:** runes whose level matters show it as a Roman numeral in the top right corner of their icon.
- Runes show a symbol in inventories for what they hold: a lasting effect, an instant effect or an ArmorPlus ability.
- Equipment shows a socket symbol in inventories (empty, partially filled or full, `showSocketOverlay`). Tooltips always show the socket count; hold Shift to see the socketed runes.
- **Rune tooltips:** hold Shift on a rune to see what it provides and how to socket it.
- **Ability descriptions:** hold the new **Effect Details** key (**Left Alt**, rebindable) with Shift to see what each ArmorPlus ability does in item tooltips. The Effects screen buttons show the descriptions too. Ender Shot's tooltip line shows its cooldown and durability cost from the config.

### Instant Effects
Instant Health, Instant Damage and Saturation now work properly on anything that lasts, instead of firing all the time.

- **Instant effects** no longer fire on every tick of their duration, on sets and runes alike.
  - When worn or held they have a cooldown of 5 seconds per effect, shared by all your gear (`instantEffectCooldown`). Instant Health and Saturation wait until they'd help, so they're ready when you need them.
  - Held tools are weaker: Instant Health heals half as much as worn armor (`heldInstantEffectStrength`).
  - Tooltips show this after the effect's name: `(5s cd)`, and `(5s cd, weaker)` for Instant Health on tool runes.
  - On weapons they fire once per hit.

### Armor Effects
You decide which effects are on, and most effects no longer need the full set.

- **One screen with tabs:** Armor, Tools, Weapons and Ranged are tabs of the same screen, so you can swap between them with the tabs or their keys. A tab with nothing to show can't be opened. Every tab lists runes and each source of effects on their own pages.
  - **Armor (G, rebindable in Controls):** see the effects of each worn set and your runes, one page each, and turn individual ones off per set, e.g. Night Vision from your Coal armor or Flight. Your choices are saved and kept on death.
  - **Tools (H, rebindable):** switch the held tool's rune effects on/off. Switched off effects stay off for all your tools.
  - **Weapons (J, rebindable):** switch the held weapon's on-hit effects on/off: its own material effects (e.g. Wither from Wither Bone weapons) and its weapon runes. Switched off effects stay off for all your weapons.
  - **Ranged (K, rebindable):** switch the held bow's rune effects on/off. Switched off effects stay off for all your bows and crossbows.
  - Server owners can disable switching with `allowEffectToggles`.
- **Effects per armor piece:** most effects no longer need the full set. They have default pieces (`effectSlots`), are only listed on those pieces, and their runes only fit into them:
  - All of the pieces, e.g. Night Vision needs the helmet and Flight needs all four.
  - Only some of them, e.g. Speed needs the leggings or the boots (`legs,feet:1`) and Regeneration any 2 pieces (`head,chest,legs,feet:2`).
  - An effect can use a piece count as its default instead, e.g. `"minecraft:speed|2"` needs any 2 pieces of a set. Its runes fit into any piece.
  - The `effectSlots` editor has a Piece Count button and Helmet, Chestplate, Leggings and Boots toggles. Use both to need only some of the switched on pieces.
- **Per-set requirements in one place:** end any `armorBuffs` entry with the pieces it needs to override the default for that set only:
  - `"minecraft:night_vision|1@head"`: the helmet of the set
  - `"minecraft:conduit_power|2@legs,feet"`: the leggings and boots of the set
  - `"minecraft:speed|1@chest,legs,feet:2"`: any 2 of the chestplate, leggings and boots of the set (listed on those three pieces)
  - `"minecraft:haste|2@2"`: any 2 pieces of the set
  - Entries without `@` use their `effectSlots` default, otherwise `defaultSetPieces` (4 = full set, as before).
  - Runes extracted from a slot-bound effect keep its slots (a Night Vision rune from `@head` only fits helmets).
- **Live tooltips:** effects are listed as `[3/4] Haste II`: how many of the pieces it needs you're wearing right now, green once it's active. The count updates as you equip, unequip or break pieces (`[0/1]` = needs this piece, `[4s]` = on-hit duration for weapons). Long effects use short names. Levels are hidden at I and for effects whose level makes no difference (`singleLevelEffects`).
- **Disabled effects are gray in tooltips:** effects and runes you switched off, and effects disabled in the config, are grayed out in item tooltips.

### Commands
New commands to check, reset and manage effects and runes without leaving the game.

- **`/ap`** works exactly like `/armorplus`.
- **`/armorplus help`** lists the commands you can use, and **`/armorplus status`** shows which of the main features are switched on.
- **`/armorplus effects [player]`** lists a player's armor, tool, weapon and ranged effects, including switched off ones; **`/armorplus effects reset [player]`** switches them all back on. Using these on other players needs OP.
- **`/armorplus rune info`** shows the details of the held rune or equipment, and **`/armorplus rune list [armor|weapon|tool|ranged]`** lists the available runes.
- **OP only:**
  - **`/armorplus rune give <players> <armor|weapon|tool|ranged> <effect> [level]`** gives players a rune.
  - **`/armorplus rune socket <armor|weapon|tool|ranged> <effect> [level]`** sockets a rune into the held equipment for free. The usual socketing rules still apply, and other mods can stop it through `RuneSocketEvent`.
  - **`/armorplus rune unsocket [effect]`** removes one or all runes from the held equipment and destroys them.
  - **`/armorplus rune sockets <count>`** sets the held equipment's socket count (needs `randomSockets`).

### Fixes & Performance
Problems solved and overhead reduced along the way.

- **Elder Guardians** now always use their own drop settings, they could pick up those of regular Guardians instead.
- **Soul Stealer** is now actually required for mob souls to drop, as its description says.
- **Mob soul advancements** now need the boss souls (Elder Guardian, Wither, Ender Dragon) instead of accepting the regular mob souls.
- Set effects are now recalculated once a second instead of every tick, reducing ArmorPlus's per-player overhead. The flight, Illumination and Warden Pacification checks run less often too, and changing equipment still applies flight and Illumination right away.

## Configs
The config is reorganized into shorter files and most long lists now have proper editors.

### New Files
Where everything lives now, and what you have to do when updating.

- **⚠ Breaking: the config is split into several shorter files, now in `config/armorplus/`.** Existing settings are not carried over, so re-apply any customizations after updating. You can delete the old `armorplus-common.toml` file from `config/`.
  - `armorplus/common.toml`: auto-smelting, availability, enchantments and advancements only.
  - **New `armorplus/effects.toml`:** the set effect rules (`defaultSetPieces`, `allowEffectToggles`, `instantEffectCooldown`, `heldInstantEffectStrength`, `effectSlots`, `singleLevelEffects`) and one section per material with its armor and weapon effects. The entry format is explained once at the top of the file instead of being repeated in every section.
  - **New `armorplus/drops.toml`:** mob and boss drops, one section per mob.
  - **New `armorplus/runes.toml`:** everything about runes:
    - turning runes on/off (the inventory socket overlay is in `armorplus/client/client.toml`)
    - socket counts and roll weights per equipment kind (`sockets.armorSockets`, `sockets.weaponSockets`, `sockets.toolSockets` and `sockets.rangedSockets`, each with `maxSockets` and `weights`)
    - extraction and socketing costs, and per-buff rules (`runeOverrides`): one entry per buff with its costs, catalysts, and whether it's extractable or disabled, e.g. `"armorplus:flight|socket=25|extract=30|catalyst=minecraft:nether_star|socket_catalyst=armorplus:astral_sigil"` (also `extractable=false` and `disabled=true`)
    - the catalysts of the ritual (`ritualCatalysts`)
    - world runes and their loot tables
    - Disabling a buff for runes keeps existing runes on items but makes them inactive.
  - **New `armorplus/client/client.toml`:** client-only options: flavor/info tooltip text and the inventory socket overlay.
- **New `armorplus/stats.toml` config:** durability, defense per piece, toughness, knockback resistance and enchantability of every armor material, and durability, mining speed, attack damage and enchantability of every tool material.
  - The defaults match the previous built-in values, so nothing changes unless you edit it.
  - Requires a restart. Servers and clients should use the same file, otherwise tooltips and durability bars show the client's values.

### Fewer Options
Options that are gone because something simpler replaces them.

- Effects can be switched off without removing them: start an entry with `!`, e.g. `"!minecraft:haste|2"`.
- Drops: `enableRegularDrops` and `enableSoulDrops` are gone; set `regularDropChance` or `soulDropChance` to `0` to disable a drop.
- Drops: the `enableTrophyDrops` option (which was disabled by the mod) and its unfinished trophy drop are removed.

### Config Screens
Less typing by hand and fewer mistakes in the in-game config.

- **New config editors:** armor and weapon effects, `effectSlots`, `runeOverrides`, `ritualCatalysts`, `worldRunes`, `runeLootSources` and `smeltingRecipes` now open a list with readable summaries and a field by field editor with searchable options, so entries no longer have to be typed in by hand. Entries with a problem are marked in red.
  - The editor scrolls and puts related fields on one row (a catalyst next to its count), and Copy and Paste buttons move an entry as text.
- **Socket weights** have their own screen that shows the chance of each socket count.
- Effect pickers now only offer the abilities that work on that type of gear, and also list abilities from other mods.
- **Find button:** config screens with many sections (materials, mob drops, stats) can now search them by name.

## API Additions
For mod and datapack authors: new ways to work with runes and react to them.

### ArmorPlusAPI
The entry point for other mods, with everything you need to read and change runes and sockets.

- **`ArmorPlusAPI`** (`com.sofodev.armorplus.api`, along with `RuneKind`, `RuneData` and `SocketResult`): create and read rune items, query, fill and empty sockets, upgrade runes, look up costs and catalysts, check a Rune Altar's circle and its pedestals, and check a player's armor, tool, weapon and ranged effects.
- **Custom abilities:** register a `RuneEffectType` in the new `armorplus:rune_effect_type` registry, with hooks for worn and held ticks, melee hits and kills, arrow shots, hits and kills, block breaks and damage taken. Rune and config entries can name it by its id. Add a `rune_effect.<namespace>.<path>.description` translation or override `getDescription()` to describe it.

### Events
Change costs and catalysts or stop things from happening. All are cancelable and fire on `NeoForge.EVENT_BUS`.

- **New developer events** (`com.sofodev.armorplus.api.event`):
  - `RuneSocketEvent`: change the ritual's cost.
  - `RuneExtractEvent`: change the Rune Altar cost and catalyst.
  - `RuneRemoveEvent`: change the cost of taking a single rune out in the Rune Altar.
  - `RuneUpgradeEvent`: change the ritual's cost and catalysts of upgrading a rune.
  - `NativeEffectUpgradeEvent`: change the ritual's cost and catalysts of upgrading an equipment effect.
  - `RuneApplyEvent` (socketed runes) and `SetEffectApplyEvent` (set and weapon material effects): fired when the equipment's effect applies while worn, held or on hit.
