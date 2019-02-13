ArmorPlus 1.12.2-11.19.0.53
----------------------------

* Localized the majority of the mod
* Fixed the special bows not working correctly with infinity and creative mode.
* Fixed special bows getting consumed if the arrow is placed in the off-hand slot 
* Fixed MAJOR performance lose because of the Skeletal King.
* Fixes for TGOTG (The Gift of The Gods) 
  - Blacklist should now work correctly
  - Made it look through the Item registry instead of the numeric id system.
* Added Textures to the prototypes (corresponding to the original ones)
* Battle axes can now disable shields
* Overhauled some of the textures for:
  - Arrows (16x16->32x32)
  - Battle Axes (16x16->64x64) - Rescaled Third-person View
  - Swords (16x16->64x64) - Rescaled Third-person View
* A few performance fixes and QoL improvements
* Commands with links now properly display information and do NOT open the link immediately. 
* _Added more bugs_

ArmorPlus 1.12.2-11.18.0.52
----------------------------

* Slowly migrating towards the new Ability System. Migration will be done in **1.13**.
* Made a registry that other mods can use to implement their own abilities.
  - Ability Registry - the registry where people can register their own abilities.
  - AbilityData - the class mods will need to extend and implement with mod-provided methods, if something is missing contact me to add more stuff.
  - ImplementedAbilities - all the implemented abilities by ArmorPlus
  - Made the ability names translatable
* Introducing abilities: Walk on Lava, Bonus XP on Kill, Wither-Proof, Potion Abilities

ArmorPlus 1.12.2-11.17.1.51
----------------------------

* Fixed CraftTweaker recipes being broken.

ArmorPlus 1.12.2-11.17.0.50
----------------------------

* Introducing experimental mode **DISABLED BY DEFAULT**, a config value that changes everything about this mod, as the name suggest its EXPERIMENTAL and can cause corruption/destroy/break your worlds.
  - New way of obtaining abilities, ability limits, quests, and a lot more (WIP)
  - Once enabled armors' will be revamped, and made so that they will require quests to be done (if any, use with caution) to obtain abilities.
* A few QoL improvements, moved most of the InGame dialogs to use the translatable key format (now can be translated).
* Fixed a few bugs related to the Skeletal King, additionally it no longer targets players but also any other type of mob, except Wither Skeletons
* GUIs:
  - Re-aligned some grid positions of the crafting GUIs, made them more correctly aligned. 
  - Added armor inventory slots to the crafting GUIs
  - JEI: Fixed some GUI arrangements
  - MAJOR: Fixed items getting voided from the GUIs

ArmorPlus 1.12.2-11.16.3.48
----------------------------

* MAJOR FIX: Blocks can be mined once again with their corresponding tools (They are no longer permanent unbreakable)
* Config addition, you can now configure a block's properties currently only:
  - You can now change block's harvest level **[(Wood/Gold) 0-3 (Diamond)]**, harvest tool **["pickaxe", "axe", "shovel"]** &/or make it unbreakable
  - You can change pretty much any block's properties from the mod (except the wip dungeon blocks)
  
ArmorPlus 1.12.2-11.16.2.47
----------------------------

* Fixed a lot of bugs related to crafting, especially items getting voided when exiting the gui.

ArmorPlus 1.12.2-11.16.1.46
----------------------------

* No longer part of TheDragonTeam, now its once again, completely under **Sokratis Fotkatzikis** aka sokratis12GR's control.
* **Updates** from now on should be displayed when they happen (updateJson, forge way's)
* **Cosmetics** should finally be working as intended & now more accessible than ever. (You can get one by donating to me via Patreon or PayPal)
  - Patreon: [https://www.patreon.com/join/sokratis12GR](https://www.patreon.com/join/sokratis12GR)
  - PayPal email: [sokratis12GR@gmail.com](https://www.paypal.com/cgi-bin/webscr?return=https://minecraft.curseforge.com/projects/armorplus?gameCategorySlug=mc-mods&projectID=237366&cn=Add+special+instructions+to+the+addon+author()&business=sokratis12GR%40gmail.com&bn=PP-DonationsBF:btn_donateCC_LG.gif:NonHosted&cancel_return=https://minecraft.curseforge.com/projects/armorplus?gameCategorySlug=mc-mods&projectID=237366&lc=US&item_name=ArmorPlus+(from+curseforge.com)&cmd=_donations&rm=1&no_shipping=1&currency_code=USD)
* **ArmorPlus** is sponsored by NodeCraft. Use code **[ArmorPlus](https://nodecraft.com/r/armorplus)** for 30% off your first month of service!

ArmorPlus 1.12.2-11.16.0.45
----------------------------

* Changed how config values for potion effects are set
* Fixed a bug where potion level was set with duration modifiers.

ArmorPlus 1.12.2-11.15.1.44
----------------------------

* Fixed a crash when disabling tinkers' armors

ArmorPlus 1.12.2-11.15.0.43
----------------------------

* Technical
  - Improved the API
  - Fixed a crash with the new config refactor
  - Improved code quality
  - Fixed crash with tinkers'
  - Recipes are now working properly (Tinkers')
* Skeletal King, now fully implemented (1/4) Bosses ready.

ArmorPlus 1.12.2-11.14.0.42
----------------------------

* Technical
  - Simplified a lof of the logic for adding effects and removing them.
  - Additionally reduced the size of duplicated operations
  - Renamed a lot of the internal code to have consistency in their names
* Fixed a bug where cobalt set effect would be impossible to achieve
* Fixed the inability to repair tconstruct armors

ArmorPlus 1.12.2-11.13.1.41
----------------------------

* Simplified the way the CraftTweaker scripts are being created in code.
* Added minHeight and maxHeight to the world gen exception if those values are set incorrect.
* Optimized World Gen slightly.
* Added a NodeCraft command
* Updated localization entries for the commands, to include modid (*this has been updated to the relative languages*)
* Updated Tile Entity registration to use the ResourceLocation version instead of the string version.
* Updated registration objects (Items, Blocks) use ResourceLocations instead of Strings for their registry names.
* Now requires TheDragonLib 1.12.2-5.3.0

ArmorPlus 1.12.2-11.13.0.40
----------------------------

* Version is now marked as release ("recommended").
* Fixed Localization on all of the supported languages (not updated the localization)

ArmorPlus 1.12.2-11.12.1.39-beta
----------------------------

* Technical: 
  - More Simplifications and optimizations
* Added the ability to configure the effects' duration, in seconds (automatically will get converted to seconds)

ArmorPlus 1.12.2-11.12.0.38-beta
----------------------------

* Technical: 
  - Made most of the configuration to be the same for all of the armors & weapons
  - Documented small parts of code
  - Major optimizations of the code & simplification of the system.
  - Re-organized a bunch of packages and a bunch of code simplifications
* Hidden the horse armor items from Creative & JEI (as they are not ready)
* Simplified & improved the ability setting on tooltip, i.e you will no longer see duplicated entries as well as have a better indication of the abilities.
* Tooltips now have a less cluttered look, removed redundant "Use: xyz", Abilities' Numbers now are converted to roman numerals i.e (1 = I, 4 = IV, 9 = IX, 10 = X, 99 = XC, 100 = C ...)
* World gen configuration has been tweaked into sub categories (for the lava crystal per dimension), simplified names
* The Super Star & Guardian Battle Axes have received a +1 dmg buff on their default values
* Tooltips have been supplied with colorful look of the abilities that they provide. Special Abilities (Not yet configurable) have their own lore.

ArmorPlus 1.12.2-11.11.1.37-beta
----------------------------

* Ultimate armor regeneration should be fixed
* Translated to Italian thanks to [Davoleo](https://github.com/Davoleo) <3
* Added a few more of the missing EMC (Including Ultimate level EMC)
* Made EMC Integration disabled by default
* Made the armors tab actually say that its an Armors tab (whoops)
* Added Team Rapture Cosmetic :blue_heart: 

ArmorPlus 1.12.2-11.11.0.36-beta
----------------------------

###Introducing a change into how updates and changes are being displayed.

* Simplification of the crafting system code (Much better)
* Added horse armor
  * This feature is still wip, some horse armor textures are working horse armors and functionality
  * In the works: Introduction of abilities, textures for them all, crafting recipes and configuration for all of these stuff
* Added the ability to set a armorMaterial type of weapons when hitting an entity to set them on fire, default disabled for all except the lava weapons
* Fixed different types of arrows having the coal arrow texture instead of their own ones
* Fixed usage of incorrect data fixers
* Added Overlord of the Guardians texture
* Animated (**VERY VERY WIP**) the bosses
* Integrations (**WIP**, **Things might be unbalanced**)
  * Added integration to ProjectE (enabled by default, can be disabled by a config option inside `config/armorplus/integrations.cfg`) 
* Fixed some important bugs causing some armors to not have any abilities when equiped in any specified config option.
* Made so that when The Ultimate Armor's invincibility has been enabled to add saturation effect. (Removed Saturation from the default effects config)
* Fixed Trophies being unable to be picked up on servers, **temporarily** disabled the pickup function of NBT modified trophies (they will keep their nbt as long as they aren't broken, if they are broken they will not save any NBT except of the original type)

ArmorPlus 1.12.2-11.10.1.35-beta
----------------------------

**Bugs Fixed**:

* Mod Crashing on server side

ArmorPlus 1.12.2-11.10.0.34-beta
----------------------------

**Features Added**:

* Mob Trophies (default drops disabled for the vanilla bosses)

* Added colored borders for all of the materials (on their tooltips)

**Features Changed**:

* Redstone weapons and Emerald weapons swapped their effects (Redstone now apply Mining Fatigue, while Emerald now applies Slowness)

* Improved the API, providing and changing access for many thins (IRepairable, IEffectHolder, IRemovable)

* Configuration files, **MAJOR** rewrite.
  * Now using forge's annotation system
  * Configs are now located inside `config/armorplus/`
  * Configs are now separated for different usages (`config.cfg`, `worldgen.cfg`, `entities.cfg`, `registry.cfg`, `misc.cfg`, `debug.cfg`, `integrations.cfg`)
  * Potion effects, applied for both weapons and armor are now configurable and no longer limited to only 1 potion effect
  * You can now enable/disable the setting on fire effect from the infused lava weapons
  * Removable potion effects for when equiped a full set, i.e Super Star armor and its anti-wither effect is now a list for multiple effects on each armor
  * You can now set each type of armorMaterial (for armors only for now), to unbreakable.
 
**Bugs Fixed**:

* Some redundant checks

* Improved the way of checking for recipes, using WildCard value instead of hardcoding it

* Removed the old sub-command system, and now using forge's sub-command system, commands remain unchanged

* Tile Entities not having their correct resource locations

* Fixed Lava Infuser's shift click functionality and smelting fuel to allow the ignition of the Lava Crystals as an energy source in addition to just being functional with a bucket of lava
  
ArmorPlus 1.12.2-11.9.3.33-beta
----------------------------

**Bugs Fixed**:

* Some armors having incorrect enchantability

ArmorPlus 1.12.2-11.9.2.32-beta
----------------------------

**Important Note**:

* **This update will require you reset/remove a few entries or even the whole config file, please create a backup of your old configuration file**

**Bugs Fixed**:

* **Config fixes**
  * Incorrect properties set for enable/disable chicken armor
  * Tinkers' Construct Config Entries are now 100% being used (finally functional)
  * Fixed some grouping of the tinkers' construct settings
  * Fixed some descriptions for the entries
  * Made disableTheUltimateArmorDeBuffs functional

ArmorPlus 1.12.2-11.9.1.31-beta
----------------------------

**Forge**:

* Requires forge version 14.23.1.2582 or later

**Features Changed**:

* Recipes when in the json mod now actually load the vanilla groups correctly and *most* of the recipes.

* Additional notes added to the gamemode's config property

**Bugs Fixed**:

* CraftTweaker's integration ZenScript string now uses string name directly instead of a static string. (outside editor/plugins/packages's support)

* JSON Recipes being incorrectly grouped in the recipe book

* Code vanilla recipes being incorrectly grouped in the recipe book

* Vanilla bench recipes wont be duplicated when using both the benches and the useJSONRecipes property

ArmorPlus 1.12.2-11.9.0.30-beta
----------------------------

**Dependencies**:

* **Shadowfact's Forgelin is no longer required**
  * Converted all of the Kotlin classes back to Java Classes

**Features Added**:
  
* Arrow now have thier names colored (even when renamed)

**Bugs Fixed**:

* Most of tinkers' construct armor config for armor enabled/disabling effects were not actually doing anything.

* A few performance issues.

* Fixed item description coloring

* Fixed some formatting

* Optimizations to how some functions and abilities are applied

ArmorPlus 1.12.2-11.8.3.29-beta
----------------------------

**Features Changed**:

* **Recipes**:
  * Tweaked/Reworked most of the Ulti-Tech Bench armor recipes.

**Bugs Fixed**:

* Obtaining Lava Nether Brick when trying to craft a workbench.

* Removed the advancements, they will be re-added once they are more complete.

ArmorPlus 1.12.2-11.8.2.28-beta
----------------------------

**Bugs Fixed**:

* Some Recipes causing crashes.

ArmorPlus 1.12.2-11.8.1.27-beta
----------------------------

**Tinkers' Construct Integration has been re-enabled**

**Bugs Fixed**:

* Some Tinkers' Construct armor recipes being messed up

* Added oreDict to the benches, fixes a some recipes requiring the oreDict version of the benches.

* Ability to duplicate items from crafting.

* Incorrect use of InventoryCrafting instead of InventoryCraftingImproved

* Incorrect use of SlotCrafting instead of the crafting manager's specific SlotCrafting

* Fixed some issues with tinkers' construct integration.

* Fixed a few bugs with the recipe handling system.

**Features Tweaked & Changed**:

* Fixed the per piece and full set effects of the armors
  
* Changed the way the effect system is being implemented
  
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

* **This version is the first beta of the 1.12 mod, which will be focused on the 1.12.2 version of Minecraft.**

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