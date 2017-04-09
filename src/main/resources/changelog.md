ArmorPlus 1.11.2-10.1.2.3
----------------------------

Bugs Fixed:

* Hopefully fully fixes crashes with Tile Entity interactions

ArmorPlus 1.11.2-10.1.1.2
----------------------------

Bugs Fixed:

* Potential Tile Entity crashes in interaction with other mods

ArmorPlus 1.11.2-10.1.0.1
----------------------------

####Balance start

Features Changed:

* Balance Changes
  * Coal armor & weapons durability nerf
  * Lapis weapons durability nerf
  * Chicken armor durability nerf
  * Slime armor durability nerf
  * Replaced Knight Slime armor's Haste II with Jump Boost II
  * Swapped the Emerald & Redstone armor abilities

* Changed config style

Bugs Fixed:

* Fixed a few more performance issues

* Night vision effect duration can can hurt some eyes on continuance use (coal armor equiped, fixes it):
        * This change applies to all armors, so even if it is set on another armor to have night vision it will constantly keep updating it

___
**List of upcoming and maybe upcoming balance changes:** https://goo.gl/7fhbzx
___

ArmorPlus 1.11.2-10.0.3.4
----------------------------

Features Added:

* Warn if the player don't have Forgelin installed

ArmorPlus 1.11.2-10.0.2.3
----------------------------

Bugs Fixed:

* Fixed a crash while using some A+ items

ArmorPlus 1.11.2-10.0.1.2
----------------------------

Bugs Fixed:

* Crash when trying to look up some items in JEI

ArmorPlus 1.11.2-10.0.0.1
----------------------------

Features Added:

* Config
  * Added a config value to enable/disable mob drops added by A+
  * Added a few config entries that had to be added a while back, but they just got added today

* Added Craft Tweaker Integration
  * Adding scripts is the exact same way as in the 1.10.2 version:

`mods.armorplus.Workbench.addShaped`

`mods.armorplus.Workbench.addShapeless`

`mods.armorplus.Workbench.remove`

`mods.armorplus.HighTechBench.addShaped`

`mods.armorplus.HighTechBench.addShapeless`

`mods.armorplus.HighTechBench.remove`

`mods.armorplus.UltiTechBench.addShaped`

`mods.armorplus.UltiTechBench.addShapeless`

`mods.armorplus.UltiTechBench.remove`

Features Changed:

* Config values are now in a look of a list, each line equals 1 of the armors/weapons/items, I made sure each line equals the representative armor/weapon/item

Bugs Fixed:

* A lot of performance issues fixed, now performance should be more smooth and mod size reduction
* Fixed repairing items
* Config
  * Fixed config values with wrong settings
  * Fixed some config entries having `(Boots, Leggings, Chestplate, Boots)` instead of `(Boots, Leggings, Chestplate, Helmet)`

Code Changes:

* A huge amount of code has been rewritten in Kotlin

Dependencies:

* Now **REQUIRES** [TheDragonLib](https://minecraft.curseforge.com/projects/thedragonlib) 1.11.2-3.0.0+ in order to work
* Now **REQUIRES** [Shadowfacts' Forgelin](https://minecraft.curseforge.com/projects/shadowfacts-forgelin) 1.3.1+

ArmorPlus 1.11.2-9.5.7.8-beta
----------------------------

Bugs Fixed:

* Prevented crashes while trying to read more information for items and armors.

ArmorPlus 1.11.2-9.5.6.7-beta
----------------------------

Bugs Fixed:

* Fixed Mob drops in the ArmorPlus towers.

ArmorPlus 1.11.2-9.5.5.6-beta
----------------------------

Bugs Fixed:

* Fixed bow related issues, finally (hopefully)

ArmorPlus 1.11.2-9.5.4.5-beta
----------------------------

Bugs Fixed:

* Literally impossible to find Lava Crystals

ArmorPlus 1.11.2-9.5.3.4-beta
----------------------------

Bugs Fixed:

* Fixed Bows, their strength and fixed a crashing issue when trying to use them.

* Added a recipe to convert back Colored Stone Brick walls into colored stone brick blocks

* A few bugs that caused performance issues

Dependency:

* Now Requires TheDragonLib 1.11.2-2.2.3 in order to work.

ArmorPlus 1.11.2-9.5.2.3-beta
----------------------------

Bugs Fixed:

* Fixed Unregistered Recipes (sorry... my bad...)

ArmorPlus 1.11.2-9.5.1.2-beta
----------------------------

JEI Integration:

* Added support for the Lava Infuser with JEI

Bugs Fixed:

* Uncraftable lava infusers

ArmorPlus 1.11.2-9.5.0.1-beta
----------------------------

###The Pre-Balancing Update
#####With a few small balance changes and bug fixes

Features Added:

* Blocks
  * Added Lava Infuser
  * Lava Infused Obsidian

Features Changed:

* Charged Lava Crystal
* A few really small performance improvements

* Recipes
    * Emerald, Obsidian & Lava armor recipes have been moved from the workbench over the high-tech bench with a little changed and more expensive recipes. 

Bugs Fixed:

* Bosses not dropping their drops on death

ArmorPlus 1.11.2-9.4.1.2-beta
----------------------------

Bugs Fixed:

* Uncraftable High-Tech Bench leading to uncraftable recipes

ArmorPlus 1.11.2-9.4.0.1-beta
----------------------------

Minecraft Version:

* Updated to 1.11.2

Features Added:

* Stone Brick Walls (Colored)

* Structure Gen:
  * You will now be able to explore Towers in your world.
  
Dependency:

* Updated to require TheDragonLib 1.11.2-2.1.0

ArmorPlus 1.11-9.3.1.2-beta
----------------------------

Features Changed:

* Made the lava crystal ore gen rarity from 7 to 8 and amount in a vein from 5 to 2

ArmorPlus 1.11-9.3.0.1-beta
----------------------------

Features Added:

* Added back JEI support for the benches.

Bugs Fixed:

* Crash on server start up.

ArmorPlus 1.11-9.2.0.1-beta
----------------------------

Features Added:

* Champion Bench (Tier 4) is added, wip, not finished, special.
* Improved the models of all the benches
* Added a new WIP mob (Ice Golem)

Code Changes:
* A few improvements here and there

Bugs Fixed:

* Fixed Shift Clicking while in the bench gui not working properly
* Fixed Workbench (from A+) not having a recipe resulting not being able to craft anything. 

ArmorPlus 1.11-9.1.1.2-beta
----------------------------

Bugs Fixed:

* Castles Spawning everywhere

ArmorPlus 1.11-9.1.0.1-beta
----------------------------

Features Added:

* Added the forges (from tier 1 to tier 3)

* Added recipes to everything (still not JEI integration for bench recipes)

Planned Features:

* Additional Game Mode (Hellish | 2)

* Change the way crafting works (Maybe)

* Bosses (Coming Soon)

* Special Abilities

* Re-Add Tinkers' Construct Integration (soon)

* Re-Add Baubles Integration (soon)

* Re-Add JEI Integration (soon)

ArmorPlus 1.11-9.0.0.1-alpha
----------------------------

Minecraft Version:

* Updated to 1.11
