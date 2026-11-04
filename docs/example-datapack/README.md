# ArmorPlus example rune datapack

Copy this folder into `<world>/datapacks/`, then re-enter the world or restart the server. `/reload` doesn't load or update runes, since they load with the world. The log shows `Loaded N datapack runes`.

Runes live at `data/<namespace>/armorplus/rune/<name>.json`.

| Field | Required | Description |
|---|---|---|
| `kind` | yes | `armor` (worn), `weapon` (on hit) or `tool` (while held; debuffs apply on hit) |
| `effect` | yes | A mob effect id (`minecraft:haste`) or an ArmorPlus ability (`armorplus:fire_extinguish`, `armorplus:ignite`, `armorplus:auto_smelt`, ...), including abilities other mods register (see [API.md](../API.md)) |
| `level` | no | Effect level (1 = I). Leave it out for abilities |
| `seconds` | no | Duration of on-hit mob effects. Abilities ignore it |
| `slots` | no | Armor runes only: the pieces it fits into (`head`, `chest`, `legs`, `feet`). Default: the effect's `effectSlots` config |
| `socket_cost` | no | Experience levels to socket it (1-39) |
| `extraction` | no | `cost`, `catalyst` (`{ "item", "count" }`) and `extractable` when the Rune Altar extracts this rune |
| `loot` | no | `[{ "table", "chance" }]`: rolled on its own. Without it, the rune joins the `runeLootSources` pool |

The `runeOverrides` config always takes precedence over `socket_cost` and `extraction`.

## Files

- `water_breathing_helmet.json`: armor, helmets only, with a custom socket cost. Can't be extracted from equipment such as Slayer armor.
- `fire_resistance_armor.json`: armor, fits chestplates or leggings, found in Nether fortresses and bastions.
- `fire_extinguish_armor.json`: armor ability with custom socket and extraction costs plus a catalyst.
- `slowness_weapon.json`: on-hit Slowness II for 4 s, common in igloos.
- `ignite_weapon.json`: on-hit ability (sets the target on fire for a random 3-8 s).
- `haste_iii_tool.json`: a rare, expensive Haste III tool rune from Ancient Cities.
- `wither_tool.json`: a tool debuff that withers mobs hit with the tool.
- `luck_tool.json`: a minimal tool rune, Luck I while held.
- `data/armorplus/.../invisibility_armor.json`: replaces the built-in Invisibility rune (same id), making it chest-only, expensive and rare.

## Notes

- A file with the same namespace and path as a built-in rune (`data/armorplus/armorplus/rune/...`) replaces it.
- Costs and extraction rules belong to the rune's kind, effect and level, so `haste_iii_tool`'s `socket_cost` doesn't affect the built-in Haste II, which uses the default cost. Two files with the same kind, effect and level both appear as runes, but only the first one's costs count (a warning is logged).
- `extraction` applies when the Rune Altar would make exactly this rune, i.e. after its level reduction (`extractionLevelReduction`): `"level": 1` matches a level II effect extracted from equipment. Tool runes are never extracted, so `extraction` has no effect on them.
