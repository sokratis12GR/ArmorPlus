ArmorPlus 1.18.2-18.2.0
----------------------------

* **Out of BETA release.**
* Re-arranged the registration order to match more closely to the actual registration order so issues are avoided.
* Internal Code Cleanup:
  * Removed a lot of unused/redundant classes and files.

ArmorPlus 1.18.2-18.1.0 (BETA)
----------------------------

* ***REMOVED MOB TROPHIES***
  * Reason: All current trophies will cause crashes and issues after being destroyed by a player and replaced.
  
ArmorPlus 1.18.2-18.0.5 (BETA)
----------------------------

* Updated **ONLY** for 1.18.2+
* Fixes TAG system related breaking changes that occur between versions.
* Fixed Lava Infused Obsidian's placed texture being invalid
* Technical: Updated to use the new World Gen Registry system/changes
    * They now must be used via the Holder<> Interface
    * registration is now done more directly combining configured features with feature and placed features with
      configured features.

ArmorPlus 1.18.1-18.0.4 (BETA)
----------------------------

* Added native support for 1.18.2 (next release only 1.18.2+)
* Added new enchant * **Soul Harden**: Your armor/wearable doesn't take any damage from anything, but takes half of
  its "max" durability as damage when the player who had it equipped previously was slain. * MAX Uses: 2 Soul Bound
  Durability uses * Added a config to check for which enchants it cannot be used with (by default mending, unbreaking,
  curse of vanishing).
* Added the following COMMON config entries
    * Mob drops added by ArmorPlus can now be enabled and/or disabled for each individual entity
        * enableRegularDrops (Default: true)
        * enableTrophyDrops (Default: true)
        * enableSoulDrops (Default: true)
* Infused Lava Crystal Pickaxe will now always infuse a crystal dropped from compressed obsidian variant of the lava
  and/or frost crystal ore (added to default config)
* Fixed & Updated the `/armorplus patreon` command which is now `/armorplus support`, and now lists both GitHub sponsors
  and Patreon as options with which one could support development.
    * Was previously using the `/armorplus wiki` localization/translations which lead to issues with execution.
* Fixed a few instances where the Fire Resistance buff wasn't applied correctly.
* Changed the Obsidian Stick recipe from a shapeless 2 obsidian requirement to a shaped recipe in 2 rows, similar to the
  wooden stick recipe but with obsidian.

ArmorPlus 1.18.1-18.0.3 (BETA)
----------------------------

- Technical Fix: Now uses saveAdditional instead of save method for tile entities.
- Added variant light levels for Crystal Ores:
    - Stone: 0; Obsidian: 4; Compressed: 8; (To fit with their environment and to provide an indicator of how much value
      they hold)
- Fixes Trident's unknown enchantment effect being applied to all tridents.

ArmorPlus 1.18.1-18.0.2 (BETA)
----------------------------

- Fixes blocks being unbreakable
- Added tool requirements for different blocks and corrected some loot drops (these changed in 1.17/1.18 so had to
  adapt).
    - all blocks for the time being, require pickaxes, ores have different tool requirement depending on the type they
      are on

ArmorPlus 1.18.1-18.0.1 (BETA)
----------------------------

- Fixes all advancements and recipes being given to the player upon picking up any item.
- Fixes all arrows not having correct ability descriptions.
- Fixes all bows not having a correct drawing animation texture.
- Fixes trophy blocks not having a correct special renderer.
- New Ore Generation settings for Lava/Frost Crystals:
    - Stone will now only spawn from Y: 0 to Y: 200, can only replace stone, air exposure is 0.5 (50%)
    - Obsidian will now only spawn from Y: -30 to Y: 0, can only replace deepslate, air exposure is 0.3 (30%)
    - Compressed will now only spawn from Y: -60 to Y: -30, can only replace deepslate, air exposure is 0.1 (10%)
- Fixed maces not having a texture and causing crashes on right/left click.
- Added the following COMMON configs
    - Special Effect Toggles (enable/disable armor & weapon effects for each material)
        - When a toggle is turned on, the effects of the set will be disabled, tooltips will show (DISABLED) on
          abilities when they have been disabled.
    - World Gen Values and Toggles (enable/disable, veinSize, exposure, minY, maxY, etc) for each Ore.

ArmorPlus 1.18.1-18.0.0 (BETA)
----------------------------

Ported to 1.18.1

- Items (Armors, Weapons, Blocks, Arrows, Souls, Materials, Etc), Effects (Buffs, Debuffs)
- World Gen is unstable (unfinished, and most likely doesn't work)
- Biomes are unstable as well
- 3D Models for some items will be broken
- Entities (except arrow entities) aren't added yet, next release