**ArmorPlus 1.10.2-8.0.0.6**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * Added the ability to set potion effects to the armors via config
    * Added the ability to color weapons' name
* **{Bugs Fixed}**
    * Fixed Armors not having a real damage reduction value
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-8.0.0.5**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * Bows/BattleAxes/Swords Damage are now configurable
    * Bows/BattleAxes/Swords Durability are now configurable
    * Added Ender Dragon Arrow
    * Changed Particles
    * Fixed dupe glitch
    * Fixed Potion Effect Particles being shown while wearing armors
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-8.0.0.4**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{JEI}**
    * Fixed world freeze/crash using 3.13.2.349 version of JEI
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-8.0.0.3**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * Added new achievemnts, tweaked the previous ones
    * Tweaked base classes
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-8.0.0.2**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Versioning}**
    * <MC>-<Major>.<Mino>.<Patch>.<Build>
* **{Bugs Fixed}**
    * Fixed Ender Dragon Zombie being buggy and its movement
* **{Features Added/Removed/Changed}**
    * Added debug info for the Ender Dragon Zombie if debugMode is true
    * Moved all the remaining recipes to their benches
    * Tiering from one to tier three is done
    * You can add/remove recipes from all three benches through mod addons/integrations
    * Added JEI support for the Ulti-Tech Bench
    * Added some little, but handly info for the bows
    * Improved how things work in code
    * Added the ability to disable armors entirely from the game via config options
    * Added Baubles integration/support
        * Added some new Cosmetics
    * Enchantments, added 2 new enchantments
        * Life Steal - when an entity is hit the player will get health points back depending on the level of the enchantment, the damage that the item will deal and some other calculations
        * Furious - Treasure Enchantment, when player is hit wearign this enchantment the player will get speed and strenght effects for a limited time depending on the level of the enchantment
    * Added new blocks
         * Red Stone Brick
         * Red Stone Brick Tower
         * Red Stone Brick Corner
         * Black Stone Brick
         * Black Stone Brick Tower
         * Black Stone Brick Corner
    * Added Recipes for the castle blocks (red/white/black stone bricks)
    * Changed benches' models and textures
    * Added support for block rotation
        * Block Lava Crystal, Block of Steel, Electrical Block, Lava Nether Brick, Compressed Obsidian
    * Tinkers Integration
        * Added Compressed Obsidian material
        * Tweaked Lava Crystal Material, now requires 2 Lava Crystals or 1 Charged Lava Crystal
        * Tweaked Fire Storm modifier, now it has 10 levels instead of 4
        * Added compatability for Steel material via ArmorPlus' steel
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-8.0.0.0.1-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * MAJOR Update
        * Refactor of "Armor Forge" to "Workbench" (Tier 1)
        * Refactor or "Advanced Armor Forge" to "High-Tech Bench" (Tier 2)
        * Addition of the "Ulti-Tech Bench" (Tier 3)
        * Moved all ArmorPlus recipes into their benches (UNDER DEVELOPMENT & UNDER CHANGES)
    * Removed Prefixes before names that were listed
    * Made all items require to have SHIFT pressed to show their tooltips
    * Hidded Dev Items from JEI
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.1.6**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{JEI}**
    * Hidden the cosmetic items from JEI
* **{Bugs Fixed}**
    * Crash on using either Armor Forge & Advanced Armor Forge
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0.5**
============================
####The Energized Tinkering Update
######Entire Changelog
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Translations}**
	* Updated French Translation of ArmorPlus (Thanks to ImmortalPharaoh7)
	* Added Russian Translation of ArmorPlus (Thanks to FTB_lag)
* **{Features Added/Removed/Changed}**
    * Tooltips on Armors now will only be displayed if LSHIFT is pressed
    * Removed unused Images & Models
    * Made the Energy Items to be able to be enchanted
    * Lava Crystal & Charged Lava Crystal are funally registered as a fuel source
    * Added a special text color for ArmorPlus people
    * Armor Forge & Advanced Armor Forge
        * Added the ability to add/remove recipes to both armor forge (for modders and addon creators)
        * Made them support OreDict
        * Fixed Functionality, and added support for Ore Dict recipes via JEI
    * Made repair cost on the Lava Armor depend on gamemode via config (Easy = Lava Crystal, Expert = Charged Lava Crystal)
* **{Bugs Fixed}**
    * Fixed a bug with Obsidian Armor Configs and Effects being wrong
    * Fixed a bug with Lapis Armor Configs and Effects being wrong
    * Fixed Ender Dragon Zombie drops
################################ Changes (dev version 1.10.2-7.0.1.0.4-dev )################################
* **{Dependencies}**
    * ArmorPlus now requires TheDragonLib 1.10.2-1.0.3.0 or later to work!
################################ Changes (dev version 1.10.2-7.0.1.0.3-dev )################################
* **{Dependencies}**
    * ArmorPlus now requires TheDragonCore 1.10.2-1.0.3.0 or later to work!
* **{Features Added/Removed/Changed}**
    * Ender Dragon Zombie will now only drop Ender Dragon Scale rather than full equipment (after this it might stay or be removed, time will say)
    * Added RF supports, Main Supported Energy API
        * RF Tools/Weapons
    * Changed Steel Armor to support mainly RF and secondary Tesla
    * Added OreDict for some things
    * Mob drops
        * Made the Wither skeleton to drop 0-1 wither bones on death
    * Removed Creative Power Cell (Temporaly, maybe)
* **{Code Changed/Added/Removed}**
    * Made BaseEnergyArmor class confusing, then made it to no longer be
    * Added a lot of base classes for all types of items/weaps supported by Energy APIs
    * Changed Tesla tools/weapons tooltips to be more accurate using own mathod rather than the premade from Tesla
################################ Changes (dev version 1.10.2-7.0.1.0.2-dev )################################
* **{Bugs Fixed}**
    * Fixed Guardian Armor's stats and achievements not working
    * Fixed a server Crash
* **{Code Changed/Added/Removed}**
    * Removed TiC.postInit from Common Proxy
################################ Changes (dev version 1.10.2-7.0.1.0.1-dev )################################
* **{Changed Format For Dev Versions}**
    * <mcversion>-<major>.<api>.<minor>.<patch>.<build>-dev
* **{Bugs Fixed}**
    * Crash using 3.11.0.269 and later version of JEI
* **{Features Added/Removed/Changed}**
    * Added Tinkers Integration
        * Added Modifiers
            * Charged Lava Crystal + Brick = Firestorm
            * The Ultimate Material + Tool/Weapon = + 1 Modifier (max uses 1)
        * Added Materials
            * Lava Crystal material
    * Blocks Added
        * WIP Spawners
    * Mobs Added
        * WIP Mini Boss Guardian
* **{Code Changed/Added/Removed}**
    * Cleaned Up some code from Main Mod file, like Ore Dicts moved to common proxy
    * Added an empty class ServerProxy - just because it should be here
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0.4-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Dependencies}**
    * ArmorPlus now requires TheDragonLib 1.10.2-1.0.3.0 or later to work!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0.3-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Dependencies}**
    * ArmorPlus now requires TheDragonCore 1.10.2-1.0.3.0 or later to work!
* **{Features Added/Removed/Changed}**
    * Ender Dragon Zombie will now only drop Ender Dragon Scale rather than full equipment (after this it might stay or be removed, time will say)
    * Added RF supports, Main Supported Energy API
        * RF Tools/Weapons
    * Changed Steel Armor to support mainly RF and secondary Tesla
    * Added OreDict for some things
    * Mob drops
        * Made the Wither skeleton to drop 0-1 wither bones on death
    * Removed Creative Power Cell (Temporaly, maybe)
* **{Code Changed/Added/Removed}**
    * Made BaseEnergyArmor class confusing, then made it to no longer be
    * Added a lot of base classes for all types of items/weaps supported by Energy APIs
    * Changed Tesla tools/weapons tooltips to be more accurate using own mathod rather than the premade from Tesla
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0.2-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
    * Fixed Guardian Armor's stats and achievements not working
    * Fixed a server Crash
* **{Code Changed/Added/Removed}**
    * Removed TiC.postInit from Common Proxy
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0.1-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Changed Format For Dev Versions}**
    * <mcversion>-<major>.<api>.<minor>.<patch>.<build>-dev
* **{Bugs Fixed}**
    * Crash using 3.11.0.269 and later version of JEI
* **{Features Added/Removed/Changed}**
    * Added Tinkers Integration
        * Added Modifiers
            * Charged Lava Crystal + Brick = Firestorm
            * The Ultimate Material + Tool/Weapon = + 1 Modifier (max uses 1)
        * Added Materials
            * Lava Crystal material
    * Blocks Added
        * WIP Spawners
    * Mobs Added
        * WIP Mini Boss Guardian
* **{Code Changed/Added/Removed}**
    * Cleaned Up some code from Main Mod file, like Ore Dicts moved to common proxy
    * Added an empty class ServerProxy - just because it should be here
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.0.0**
============================
####The Birth Of Something New
######Entire Changelog
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Dependencies}**
    * ArmorPlus now requires TheDragonCore 1.10.2-1.0.2.0 or later to work!
* **{Features Added}**
    * Creative Tab for Tesla Items/Blocks/Weapons
* **{Code Changed/Added/Removed}**
    * Moved all the Tesla Items/Blocks/Weapons to the Tesla Creative Tab
    * Added @Optional Annotations to all Tesla stuffs, so they work only if Tesla is installed
################################ Changes (dev version 1.10.2-7.0.2.0-dev )################################
* **{Features Added/Removed/Changed}**
    * Tesla Items
        * Added Tesla Axe
        * Added Tesla Rod
        * Added Tesla Shovel
        * Added Tesla Hoe
    * The Gift Of The Gods now will have 2 uses before it gets consumed, and if the player uses the gift of gods while in stack it will only consume one of them.
    * The Gift Of The Gods now has cooldown 30 seconds
    * Steel Armor now will have Tesla as durability
    * New Textures for the Tesla Tools/Weapons
    * Added a config option for all the Gift Of The Gods things, and debugMode for it
    * Made all aniumated textures a bit more smooth
    * Added config options for most of the tesla items' propeties
* **{Code Changed/Added/Removed}**
    * Made Tesla Weapons/Tools animated using mcmeta
    * Rewrited how (GOTG) works (now drops the item instead of replacing the held one, and made it to have more than 1 uses)
    * added more config options
    * Registered the new Items
    * Updated Client Config Gui's list to include all the config options
    * Added/Changed recipes of the tesla tools/weapons to require Tesla Rod instead of Stick
    * Gradlew Clean Up
* **{Dependencies}**
    * Tesla (1.2.1.49 or later) (Optional) but required for Tesla Tools/Weapons and v2 armors
################################ Changes (dev version 1.10.2-7.0.1.0-dev )################################
* **{Features Added/Removed/Changed}**
    * Tesla Items/Blocks
        * Tesla Sword
        * Tesla Pickaxe
        * Creative Power Cell (Tesla)
    * More Cosmetic Items for special people
        * MMD Logo Cosmetic
    * Removed Teleport Command, Dimension ArmorPlus
    * Changed GUI Scale and Background Image of the ArmorPlus Books
    * Removed Night Vision from the Ultimate Armor
* **{Code Changed/Added/Removed}**
    * Added BaseEnergyBlock (Used by the tesla blocks as a base)
    * Added Cofh/API to main code for later usage mayble - if not used it will be removed
    * Added BaseTesla (Used by the tesla tools/weapons as a base)
    * Item Tesla Sword
    * Removed all the code for the Dimension ArmorPlus
    * Removed the ARPTP command, cheaty, unneeded
    * Changed Values of the GUIs for the ArmorPlus Books so they match their new GUI Texture by (Aya Shameimaru)
    * Added the `hasTesla` Method so it checks if Tesla is loaded
    * Added Recipes for the Tesla tools/weapons
    * Removed Night Vision Key Bind
################################ Changes (dev version 1.10.2-7.0.0.0-dev )################################
* **{Features Changed}**
    * Full Code Rewrite
    * Lava Armor now if is in the water with water breathing effect wont apply any debuffs nor break the armor
* **{Features Added}**
    * More Cosmetic Items for special people
        * Twitch Logo Cosmetic
        * Beam Logo Cosmetic
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.2.0-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * Tesla Items
        * Added Tesla Axe
        * Added Tesla Rod
        * Added Tesla Shovel
        * Added Tesla Hoe
    * The Gift Of The Gods now will have 2 uses before it gets consumed, and if the player uses the gift of gods while in stack it will only consume one of them.
    * The Gift Of The Gods now has cooldown 30 seconds
    * Steel Armor now will have Tesla as durability
    * New Textures for the Tesla Tools/Weapons
    * Added a config option for all the Gift Of The Gods things, and debugMode for it
    * Made all aniumated textures a bit more smooth
    * Added config options for most of the tesla items' propeties
* **{Code Changed/Added/Removed}**
    * Made Tesla Weapons/Tools animated using mcmeta
    * Rewrited how (GOTG) works (now drops the item instead of replacing the held one, and made it to have more than 1 uses)
    * added more config options
    * Registered the new Items
    * Updated Client Config Gui's list to include all the config options
    * Added/Changed recipes of the tesla tools/weapons to require Tesla Rod instead of Stick
    * Gradlew Clean Up
* **{Dependencies}**
    * Tesla (1.2.1.49 or later) (Optional) but required for Tesla Tools/Weapons and v2 armors
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.1.0-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added/Removed/Changed}**
    * Tesla Items/Blocks
        * Tesla Sword
        * Tesla Pickaxe
        * Creative Power Cell (Tesla)
    * More Cosmetic Items for special people
        * MMD Logo Cosmetic
    * Removed Teleport Command, Dimension ArmorPlus
    * Changed GUI Scale and Background Image of the ArmorPlus Books
    * Removed Night Vision from the Ultimate Armor
* **{Code Changed/Added/Removed}**
    * Added BaseEnergyBlock (Used by the tesla blocks as a base)
    * Added Cofh/API to main code for later usage mayble - if not used it will be removed
    * Added BaseTesla (Used by the tesla tools/weapons as a base)
    * Item Tesla Sword
    * Removed all the code for the Dimension ArmorPlus
    * Removed the ARPTP command, cheaty, unneeded
    * Changed Values of the GUIs for the ArmorPlus Books so they match their new GUI Texture by (Aya Shameimaru)
    * Added the `hasTesla` Method so it checks if Tesla is loaded
    * Added Recipes for the Tesla tools/weapons
    * Removed Night Vision Key Bind
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-7.0.0.0-dev**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Changed}**
    * Full Code Rewrite
    * Lava Armor now if is in the water with water breathing effect wont apply any debuffs nor break the armor
* **{Features Added}**
    * More Cosmetic Items for special people
        * Twitch Logo Cosmetic
        * Beam Logo Cosmetic
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.9.2**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
    * Fixed Ender Dragon Zombies Drop Rates
    * Fixed code variables
    * Fixed some problems with bows
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.9.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
    * A recipe bug with tinkers construct armors.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.9.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
    * Special Cosmetic for special people
* **{Fratures Changed}**
    * Lava Armor will now extinguish the fire overlay if the full set effect is set to false and the player is wearing 1 of the armor pieces
    * ARP Dimensions will be disabled by defualt
    * Structure Castle Generation is set to 0 by default, until it is fully done
    * Ender Dragon Zombies will have 40.0D HP by default and their movement speed is increased to 0.20D
* **{Bugs Fixed}**
    * No Description and no name for the achievement "Welcome to ArmorPlus" 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.8.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
    * Crash when loaded armorplus on a sever
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.8.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Changed}**
    * Armors again will be single set effect by default
    * NBT Item (Dev Item) (Ignore - Added for future planed releases)
* **{Features Added}**
    * Keybind to enable/disable the Night Vison effect of The Ultimate Armor (Default: "N")
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.7.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
	* World Gen Castles [WIP]
	* If not the full set of the Ultimate Armor is equipped the player can't move
	* Improved White Stone Brick blocks to work better with BlockStates
	* Lava Crystal Sub Items
	* Lava Crystal and Charged Lava Crystal
* **{Features Changed} **
	* Changed the recipe of the Armor Forge now uses Lapis Lazuli instead of lapis blocks
* **{Features Removed}**
	* All the 4 Reinforced Sets
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.6.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Changed}**
	* Lava Armor no longer have the resistance effect.
	* Lava Armor in contact with water will give the player slowness 2 and will damage the player with water damage and the armor will lose durability each tick.
	* All armors will now be full set effect only by default.
	* Integration with the one probe is now optional but will require you to craft the helmet with the probe in order to combine.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.5.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed With Compatibility}**
	* Crash jei because of duplicated jei categories
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.5.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
	* White Stone Brick
	* White Stone Brick Tower
	* White Stone Brick Corner
	* Added JEI Support for the Advanced Armor Forge
* **{Bugs Fixed}**
	* Names not displaying correctly
	* Fixed a crash on easy mode
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.4.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
	* Missing Texture on some Items/Blocks
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.4.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Changed}**
	* Fixed some compatability issues in some situations
	* Unlocalized names are now all lowercase
	* Textures names are now all lowercase
	* Model names are now all lowercase
	* Resource Location names are now all lowercase
	* Language File changed the item's unlocalized names
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.3.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Balancing Update}**
	* Reduced Ender Dragon Zombie's HP from 100 to 20
* **{Features Added}**
	* Added a config option for all Ender Dragon Zombie Properties (hp, damage, knockback resistance etc..)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.2.4**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Balancing Update}**
	* Fixed Ender Dragon Zombie Spawn Rates
* **{Bugs Fixed}**
	* Wrong Loot Table for the Ender Dragon Zombie
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.2.3**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Balancing Update}**
	* Fixed Ender Dragon Zombie Spawn Rates
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.2.2**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
	* Fixed Ender Dragon Zombie not rendering the Ender Dragon Battle Axe
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.2.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
	* Server Crash on Start up
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.2.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
	* Entities (Monsters)
		* Ender Dragon Zombie (will spawn with full Ender Dragon Armor and Battle Axe)
	* Dimensions
		* ARP Dimension (WIP) (WARNING: Dangerous)
	* Blocks
		* Lava Nether Brick
		* Lava Cactus
	* DebugMode (WIP)
		* If DebugMode is enabled you can view how many items are dropped from a mob with custom drops from ArmorPlus' Config (ex: "Ender Dragon Dropped: armorplus:ender_dragon_scale x 12)
	* A new Dev Tool (WIP)
		* Prints the current (exact) health of the entity, that the item was used on. (ex: "[ Zombie ] - Health: 20")
* **{Features Removed}**
	* Ender Dragon Egg Recipe (Useless for the Mod)
* **{Features Fixed}**
	* World Gen should work perfectly now ^^
* **{Features Changed}**
	* Block of Steal & Electric Block can now be used as beacon base
	* Added Light Level to the Block Lava Crystal
	* Each Type of bow has its own shot power damage
	* The Ultimate Armor is now unbreakable, added Mending to it, and made it unable to be combined with other enchantments
	* Changed some deprecated code
	* Updated some old code information
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.1.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
	* Arrows
		* Lapis Arrow (Gives the enemy Nausa)
		* Redstone Arrow (Gives the enemy Slowness)
		* Lava Arrow (Sets the enemy on Fire)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.0.1**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Bugs Fixed}**
	* Lava Crystal Ore spawn being between y level 58 and 8 rather than 16 and 6
* **{Features Added}**
	* Added the ability to disable the DeBuffs from the Ultimate Armor
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**ArmorPlus 1.10.2-6.0.0.0**
============================
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* **{Features Added}**
	* The One Probe Integration
	* In Game Config GUI support
	* Added Advanced Armor Forge
	* Added a new Crafting System
	* Added Arrows (WIP)
	* Fixed Bows
	* Added Elytra Crafting
* **{Features Changed}**
	* Nerfed the Crafting recipes cost of all Tinkers Armors in expert mode
	* Replaced a lot of the old recipes, changed them and moved them to the Advanced Armor Forge
	* Tweaked the Configuration File (I can't promise its gonna be for last)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
