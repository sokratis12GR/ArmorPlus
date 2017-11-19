ArmorPlus 1.12.2-11.8.0.26-beta
----------------------------

**Tinkers' Construct Integration temporary disabled** (Will come back)

**Features Added**:

* Added Just Enough Items Integration & CraftTweaker Integration to the Lava Infuser

* Tiered Benches are back again (**Workbench, High-Tech Bench, Ulti-Tech Bench, Champion Bench**)
  * Improved their performance and removed duplicated code.
  * CraftingTweaker Integration Upcoming (**Can be buggy, please report any issues in the [GitHub Issues](https://github.com/TheDragonTeam/ArmorPlus/issues)**)
  * Just Enough Items Integration (**Can be buggy, please report any issues in the [GitHub Issues](https://github.com/TheDragonTeam/ArmorPlus/issues)**)

* **Recipes**
  * Re-Added the recipes for the decorative blocks, for the benches and etc.
  * Moved some recipes over the A+'s Workbench
  * Added a few more recipes
  * **Re-Crated most of the old crafting recipes for ALL OF THE BENCHES**
  * Re-added the good old tiered recipes
  * Added an option to choose between the tiered recipes and the all in recipes in the crafting table (3x3) via json

* Gamemodes, they are back too. **BUT Repair Material is the same for Easy and Expert**

**Bugs Fixed**:

* Moved all the effect changes and checks outside of the Global onArmorTick(**PlayerTickEvent&**) event handler.
    * Fixed insane amounts of time outs caused by the mod
    * Fixed client issues when connecting to a server, too many checks per tick to be handled by the player/client
    * Optimized the armor effects handling better, inside the Armor's properties.
    * Removed the amounts of duplicated code
    * Added a few more utilities

* Some cosmetics no longer appear in the JEI nor in the Creative Tabs (A+ Items)

ArmorPlus 1.12.2-11.7.2.24-beta
----------------------------

**Bugs Fixed**:

* A million Nether Towers generating around the world

ArmorPlus 1.12.2-11.7.1.23-beta
----------------------------

**Bugs Fixed**:

* Wrong condition set on the Compressed Obsidian recipe, making it uncraftable

ArmorPlus 1.12.2-11.7.0.22-beta
----------------------------

#####Stability - Please Read

**Note**: 

* **This version is the first beta of the 1.12 mod, which will be focused on the 1.12.2 version of mc.**
* **WIP and experimental features will not find their place in the mod. At least for now.**
* **This release aims to be the first playable version of the mod**
* **While some unfinished wip features are being removed, they WILL come back in the future, ready to be in use.**

ArmorPlus 1.12.1-11.6.4.21-alpha
----------------------------

**Bugs Fixed**:

* Cosmetics not working

ArmorPlus 1.12.1-11.6.3.20-alpha
----------------------------

**Bugs Fixed**:

* Tinkers' Construct Integration now works 100% (all features re-implemented)
    * Modifiers work (FireStorm & Extra Modifier)
    * Materials work (Infused Lava Crystal, Infused Obsidian & Compressed Obsidian)
    
ArmorPlus 1.12.1-11.6.2.19-alpha
----------------------------

**Bugs Fixed**:

* Crash with latest Tinkers (for some reason a modifier was being registered twice)

ArmorPlus 1.12.1-11.6.1.18-alpha
----------------------------

**Features Changed**:

* Some block textures added and a new model for the Ender Pillar

* Skeletal King (WIP) progress, still not ready for even partial release

**Bugs Fixed**:

* Wrong version being used as Mod Version

* Experimental World Gen being accidentally applied...

ArmorPlus 1.12.1-11.6.0.17-alpha
----------------------------

**Features Changed**:

* The Wither boss minions got a minor change, should work now... max amount to spawned minions has been added

**Performance**:

* Improved a lot of code and/or added/changed some others to work better

**Bugs Fixed**:

* Crash at startup caused by ModItems.java (hopefully)

**Dependencies**:

* Updated to Minecraft 1.12.1

* Now requires TheDragonLib 1.12.1-5.0.0

* Now requires Forge 14.22.0.2462

ArmorPlus 1.12-11.5.1.16-alpha
----------------------------

**Bugs Fixed**:

* Crash on startup, fixed a not full check for the tinkers' integration

ArmorPlus 1.12-11.5.0.15-alpha
----------------------------

**Features Changed**:

* Now there is an option for the nether tower if it should only spawn in the overworld (default: true)
* The Nether Towers will now spawn on all "HOT" type areas, except types: "SAVANNA" & "JUNGLE"

**Bugs Fixed**:

* Some tool tips not being localized

ArmorPlus 1.12-11.4.0.14-alpha
----------------------------

**Bugs Fixed**:

* Crash when loading with latest tinkers construct

**Features Added**:

* Configs for tinkers construct
    * The ability to enable/disable the integration

ArmorPlus 1.12-11.3.6.13-alpha
----------------------------

**Features Changed**:

* Restricted the `Nether Towers` to only spawn in the `Overworld` and only spawn in desert biomes `Desert, Desert Hills, Mutated Desert`

ArmorPlus 1.12-11.3.5.12-alpha
----------------------------

**Features Added**:

* Added some more progress towards the Skeletal King (Boss)

* Added a conditional check to see if the item for a recipe exists before registering it to prevent it from stopping other recipes to load

**Bugs Fixed**:

* Crash on startup when disabling items, finally fixed... (this time 100% sure)

ArmorPlus 1.12-11.3.4.11-alpha
----------------------------

**Bugs Fixed**:

* Now correctly will generate `Ore Lava Crystal` in the world instead of `Block Lava Crystal`

ArmorPlus 1.12-11.3.3.10-alpha
----------------------------

**Features Added**:

* A few very needed recipes for lava crystal blocks/items (compressed blocks -> blocks, blocks -> crystals)

* A small description in the Dev Tool

* Added a few checks before allowing dev environment debug things to happen

**Bugs Fixed**:

* Dev Environment being set to true and causing a massive amount of Nether Towers to spawn

ArmorPlus 1.12-11.3.2.9-alpha
----------------------------

**Bugs Fixed**:

* Equipping any set of armor gives you debuffs

ArmorPlus 1.12-11.3.1.8-alpha
----------------------------

* Updated to forge 14.21.1.2412 or later

ArmorPlus 1.12-11.3.0.7-alpha
----------------------------

**Breaking Changes**:

* Updated to forge 14.21.1.2387 or later

**Features Added/Changed/Removed**:

* Tweaked the Lava Tower a bit
    * Made it slightly harder than before.
    * Fixed a few bugs with it.
    * Improved the traps, just a little bit.

* Improved a little bit the dev tool

**Bugs Fixed***

* Crash on startup

ArmorPlus 1.12-11.2.0.5-alpha
----------------------------

**Breaking Changes**:

* Updated to forge 14.21.0.2359 or later

* Moved over the new forge registry system

* Requires TheDragonLib 1.12-4.0.1 or later

**Features Added/Changed/Removed**:

* Removed the custom gamemode system (always on expert mode) //Others were redundant, #soon-will-be-replaced

* Added recipes (basic, expert) for the armors and such

* Added a new boss "Skeletal King" VERY WIP, EXPERIMENTAL, BE CAREFUL when using him

* Tweaked some code and variables, organized the model file structure and a few other improvements

ArmorPlus 1.12-11.0.1.2-alpha
----------------------------

**Features Added/Changed/Removed**:

* Removed all the types of benches (you will be missed!)

**Bugs Fixed**:

* [Creative Tab Leak (fixed thanks to TheCodedOne)](https://www.reddit.com/r/feedthebeast/comments/6h9z7f/psa_the_creativetab_leak_glitch)

ArmorPlus 1.12-11.0.0.1-alpha
----------------------------

**Important Information**:

* These versions are alphas, they may be unstable and may corrupt your world

* No recipes work with the current version

**Minecraft**:

* Updated to 1.12

**Dependencies**:

* Requires forgelin 1.5.0+