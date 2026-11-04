# 26.1.2 - 23.0.0

## Dependencies
- **TheDragonLib 1.3.0 or newer is now required**, so update it alongside ArmorPlus.
- **Optional JEI support:** a Rune Extraction category, rune socketing in the Anvil category, and each rune listed separately.

## New & Changed Features

### Runes
- **New rune system:** extract the power of ArmorPlus equipment into runes and socket them into other armor, weapons and tools, including vanilla and other mods' equipment.
- **Crafting:** Blank Runes (2 per craft: smooth stone, amethyst shards and lapis), the Rune Altar (amethyst shards, a Blank Rune, diamonds and obsidian) and the Socket Punch (diamond, amethyst shard and obsidian stick).
- **Rune Altar:** place ArmorPlus equipment and a Blank Rune, choose which of its buffs to extract and pay experience levels.
  - Extracted runes are one level weaker than the original buff (never below I). The equipment is used up by default; with `destroySourceOnExtract = false` it loses 50% of its durability instead (`extractionDurabilityCost`).
  - Strong buffs cost more and need a catalyst: Flight, Warden Pacification, Wither Immunity and Conduit Power each need a Nether Star.
- **Armor & Weapon Runes:** socket them into equipment in an Anvil. Armor runes work per piece, weapon runes apply their effect on melee hits.
  - A rune can't be socketed if the equipment already has that buff, or the same rune is already socketed.
  - When several worn pieces give the same buff, only the highest level counts.
  - Flight from a socketed rune uses the existing flight handling, so it still only removes flight ArmorPlus granted and doesn't conflict with other mods.
  - Socketing costs 3 levels by default; strong runes cost more (e.g. Flight costs 25 levels).
  - Grinding equipment in a Grindstone removes its runes and **destroys them by default** (`returnRunesOnRemoval` gives them back); enchantments stay until it's ground again.
- **Tool Runes:** pickaxes, shovels, hoes and other tools (`#minecraft:enchantable/mining`, except axes, which count as weapons) get their own sockets and accept tool runes, whose effects work while the tool is held in your main hand.
  - Built-in tool runes: **Haste II**, **Night Vision**, **Speed**, **Fire Resistance**, **Water Breathing** and the new **Auto-Smelt** (`armorplus:auto_smelt`: mined blocks drop their smelted result, using the `smeltingRecipes` list of the Infused Lava tools). They are always available in the AP Runes tab, JEI and world loot; `tool|...` entries in `worldRunes` add more.
  - Tool effects are separate from armor effects: tool abilities like Auto-Smelt can't be used as set effects, and set abilities like Flight can't be put on tool runes.
  - New **Tool Effects** screen (**H**, rebindable): switch the held tool's rune effects on/off. Switched off effects stay off for all your tools.
  - Tool runes can also carry on-hit debuffs like weapon runes (`minecraft:wither`, `minecraft:poison`, `minecraft:slowness`, ..., and `armorplus:ignite`): they're applied to the mobs you hit with the tool, not to you.
  - ArmorPlus pickaxes and shovels count as tools: they take tool runes, while extracting from them in the Rune Altar gives weapon runes of their on-hit effects. Axes, swords, battle axes and maces count as weapons.
- **Random sockets:** equipment rolls 0-2 sockets when it's crafted or once it's in a player's inventory, including gear from loot, trades or older worlds (50% none, 40% one, 10% two). Smithing upgrades keep the sockets.
- **Socket Punch:** adds one socket to equipment in an Anvil for 10 levels, up to the maximum. Needs `randomSockets` enabled.
- **World runes:** runes of most vanilla effects can be found in chests throughout the world, from dungeons, mineshafts, temples and shipwrecks to Ancient Cities and End Cities (see `runeLootSources`). Only Instant Health, Instant Damage, Saturation and the omens aren't found in the world.
- **Datapack runes:** runes can be added with datapacks at `data/<namespace>/armorplus/rune/<name>.json`, e.g. `{ "kind": "tool", "effect": "minecraft:haste", "level": 2 }`. They are synced to clients and show up in the AP Runes tab, JEI and world loot.
  - Optional fields: `seconds` (on-hit duration), `slots` (armor pieces, e.g. `["head"]`), `socket_cost`, `extraction` (`cost`, `catalyst` `{ "item", "count" }`, `extractable`) and `loot` (`[{ "table", "chance" }]`, rolled on its own; runes without `loot` use the `runeLootSources` tables).
  - The built-in tool runes and world runes are datapack files too, so datapacks can replace them. The `worldRunes` config is empty by default and only adds extra runes; `runeOverrides` wins over the datapack costs.
- Equipment shows a socket symbol in inventories (empty, partially filled or full). Tooltips always show the socket count; hold Shift to see the socketed runes.
- New **AP Runes** creative tab with the Rune Altar, Blank Rune, Socket Punch and one rune of every available buff and every vanilla effect, grouped into armor, weapon and tool runes.

### Instant Effects
- **Instant effects** (Instant Health, Instant Damage, Saturation) no longer fire on every tick of their duration, on sets and runes alike.
  - When worn or held they have a cooldown of 5 seconds per effect, shared by all your gear (`instantEffectCooldown`). Instant Health and Saturation wait until they'd help, so they're ready when you need them.
  - Held tools are weaker: Instant Health heals half as much as worn armor (`heldInstantEffectStrength`).
  - Tooltips show this after the effect's name: `(5s cd)`, and `(5s cd, weaker)` for Instant Health on tool runes.
  - On weapons they fire once per hit.

### Armor Effects
- **Switch effects on/off:** press **G** (Armor Effects, rebindable in Controls) to see the effects of each worn set and your runes, one page each, and turn individual ones off per set, e.g. Night Vision from your Coal armor or Flight. Your choices are saved and kept on death.
- **Weapon Effects screen:** press **J** (rebindable) to switch the held weapon's on-hit effects on/off: its own material effects (e.g. Wither from Wither Bone weapons) and its weapon runes. Switched off effects stay off for all your weapons.
- **One screen with tabs:** Armor, Tools and Weapons are tabs of the same screen, so you can swap between them with the tabs or their keys. A tab with nothing to show can't be opened.
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
- Server owners can disable switching with `allowEffectToggles`.
- Set effects are now recalculated once a second instead of every tick, reducing ArmorPlus's per-player overhead.

## Configs
- **⚠ Breaking: the config is split into several shorter files.** Options that moved or were renamed are reset to their defaults, so re-apply any customizations after updating. You can delete the old `armorplus-common.toml` to start clean.
  - `armorplus-common.toml`: auto-smelting, availability, enchantments and advancements only.
  - **New `armorplus-effects.toml`:** the set effect rules (`defaultSetPieces`, `allowEffectToggles`, `instantEffectCooldown`, `heldInstantEffectStrength`, `effectSlots`, `singleLevelEffects`) and one section per material with its armor and weapon effects. The entry format is explained once at the top of the file instead of being repeated in every section.
  - **New `armorplus-drops.toml`:** mob and boss drops, one section per mob.
  - **New `armorplus-runes.toml`:** everything about runes:
    - turning runes on/off (the inventory socket overlay is in `armorplus-client.toml`)
    - socket counts and roll weights per equipment kind (`sockets.armorSockets`, `sockets.weaponSockets` and `sockets.toolSockets`, each with `maxSockets` and `weights`)
    - extraction and socketing costs, and per-buff rules (`runeOverrides`): one entry per buff with its costs, catalyst, and whether it's extractable or disabled, e.g. `"armorplus:flight|socket=25|extract=30|catalyst=minecraft:nether_star"` (also `extractable=false` and `disabled=true`)
    - world runes and their loot tables
    - Disabling a buff for runes keeps existing runes on items but makes them inactive.
  - **New `armorplus-client.toml`:** client-only options: flavor/info tooltip text and the inventory socket overlay.
- **Fewer options to scroll through:**
  - Each material's `disabledBuffs` list is gone: start an entry with `!` to switch it off without removing it, e.g. `"!minecraft:haste|2"`.
  - Drops: `enableRegularDrops`, `enableSoulDrops`, `enableBossRegularDrops` and `enableBossSoulDrops` are gone; set `regularDropChance` or `soulDropChance` to `0` to disable a drop.
  - Drops: the `enableTrophyDrops` option (which was disabled by the mod) and its unfinished trophy drop are removed.
- **New `armorplus-stats.toml` config:** durability, defense per piece, toughness, knockback resistance and enchantability of every armor material, and durability, mining speed, attack damage and enchantability of every tool material.
  - The defaults match the previous built-in values, so nothing changes unless you edit it.
  - Requires a restart. Servers and clients should use the same file, otherwise tooltips and durability bars show the client's values.
- **New config editors:** armor and weapon effects, `effectSlots`, `runeOverrides`, `worldRunes`, `runeLootSources` and `smeltingRecipes` now open a list with readable summaries and a field by field editor with searchable options, so entries no longer have to be typed in by hand. Entries with a problem are marked in red.
- **Socket weights** have their own screen that shows the chance of each socket count.
- Effect pickers now only offer the abilities that work on that type of gear, and also list abilities from other mods.
- **Find button:** config screens with many sections (materials, mob drops, stats) can now search them by name.

## API Additions
- **`ArmorPlusAPI`** (`com.sofodev.armorplus.api`): create and read rune items, query and fill sockets, and check a player's armor and tool effects.
- **Custom abilities:** register a `RuneEffectType` in the new `armorplus:rune_effect_type` registry, with hooks for worn and held ticks, melee hits, block breaks and damage taken. Rune and config entries can name it by its id.
- See `docs/API.md` in the repository for the API and `docs/example-datapack` for datapack runes.
- **New developer events** (`com.sofodev.armorplus.api.event`, all cancelable, on `NeoForge.EVENT_BUS`):
  - `RuneSocketEvent`: change the Anvil cost.
  - `RuneExtractEvent`: change the Rune Altar cost and catalyst.
  - `RuneApplyEvent` (socketed runes) and `SetEffectApplyEvent` (set and weapon material effects): fired when the equipment's effect applies while worn, held or on hit.
