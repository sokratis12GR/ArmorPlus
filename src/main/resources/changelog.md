ArmorPlus 1.16.5-16.4.0 (BETA)
----------------------------

* From now on, supporting only 1.16.5+
* Minimum required forge version 36.0.42
* Added Lava Shards & Frost Shards, they can be turned into crystals using 3x3 shards
* Added Castle & Colored Brick Stairs & Slabs with recipes to match them
* Added Snow Bricks (crafted using 2x2 Snow blocks), Stairs (crafted like regular stairs) and Slabs (crafted like regular slabs)
* Changed the hardness and harvest level of the lava & frost crystal ores and their drops for each variant.
  - Stone: Now drop 1-2 shards, require Iron Pickaxe or higher harvest level, fastest to mine.
  - Obsidian: Now drop 3-7 shards, require Diamond Pickaxe or higher harvest level, faster to mine.
  - Compressed: Drops are unchanged (1-2 crystals), require Netherite Pickaxe or higher harvest level, same mining speed.
* Introducing the Soul Exchanger (Villager Profession), by having a villager next to x, they will obtain this profession.
  - Provides a way to get frost & lava shards and crystals, blaze rods/powder, minor souls, major soul exchange as well as a way to obtain Soul Stealer books and/or enchanted weapons. 
* Removed Castle Block & Colored Brick Type to Block recipes(i.e. Wall -> Block, Corner -> Block);
* Fixed a crash on world load related to Entity Types being null when mods like Reliquary, Better Diving, Minecolonies are present. (Hopefully).

ArmorPlus 1.16.5-16.3.1 (BETA)
----------------------------

**QUICK-FIX**: Fixed a crash when booting and mekanism is not present. Fixes #248

* Fixed super star bow pulling animation not being correct.

ArmorPlus 1.16.5-16.3.0 (BETA)
----------------------------

* Added support for 1.16.5
* Added Patchouli integration/support, 1st edition will not contain everything, will be updated alongside the mod
  updates.
* Added ToolTips to the armors, weapons, tools that display their conditions and their effects (and weaknesses)
* Added some commands "/armorplus <help, info, nodecraft, discord>"
* Added a new treasure enchantment "Unknown", in the future will provide a special effect based on the item it is
  applied to.
* Added Demonic Dragon (WIP) boss entity, a very wip model + texture + animation (No functionality atm)
* Added a new item "Wooden Rod", crafted via a shapeless recipe of 4 sticks together.
    - It replaces the current sticks in mace recipes
* Added recipes for the slime and chicken armors.
* Added ElementalMastery Capability
* Added new "buffs": Fire Weakness, Natural Immunity (Provides Protection & Fire Resistance)
* Added a few advancements to keep track of your A+ progress.
* Added a special celebration item & advancement
* Made various currently available items (obsidian+ tier items) to be immune to lava
* Replaced the obsidian in the obsidian armor recipe with compressed obsidian (as it should have been).
* Replaced sticks in infuse lava tier + maces with obsidian sticks
* Adjusted some armor protection, toughness and knockback resistance values of several armors
* Fixed Flight equipment check conflicting with other mods (hopefully). Fixes #245
* Fixed Water Weakness not being applied correctly.
* Fixed repair materials not working for all items.
* Fixed some recipe miss-matches with Colored Stone Bricks.
* Fixed some items having incorrect colored names.
* Fixed armor items having incorrect durability values.
* Fixed GeckoLib animations not working in the newer versions of GeckoLib due to change in the controller usage.
* Fixed silent major issues caused with the model registering, fixed properly this time.

Technical:

* Added Elements (affinities) for future expansions, Added Frost Armor, Frost-Lava armor (obviously dev only).
* Added ElementalMastery(Element primary, Element secondary) capability for future use.
* Organized the language file (en_us.json), everything now should be inside a category/sub-category depending on what
  use it has.

ArmorPlus 1.16.3-16.2.2 (BETA)
----------------------------

* Fixed world crash on startup when using other mods that check the entity registry. **Fixes #243 (1/2)**
* Fixed animation of the entities not existing and causing crashes. **Fixes #243 (2/2)**

ArmorPlus 1.16.3-16.2.1 (BETA)
----------------------------

* Ensures ArmorPlus requires GeckoLib 3 to be present to prevent weird errors.

ArmorPlus 1.16.3-16.2.0 (BETA)
----------------------------

* **GeckoLib 3** is now a **required** dependency
    - What does this mean? Well now, modeling and animation will be much easier. Entities, armors, weapons and even some
      items will get new models/animations.
* The obsidian & stone variants of lava crystal and frost crystal ores should now drop the correct crystal. **Fixes
  # 240 (1/2)**
* **Lava Crystals** should now infuse when interacting with lava. **Fixes #240 (2/2)**
* **ConfiguredFeatures** should no longer cause issues with world generation! **Fixes #241**
* **Lava Crystals** (Compressed/Original variant) now drop 1-2 crystals per ore, fortune affects it.
* **Wither Skeleton Boss** now uses Geckolib's animation system and is now animated, still not fully functional, no boss
  stages, fights, location yet.
* **Added WIP Maces**
    * Maces have a "**weight**" factor
        * **LIGHT**: faster attack speed, weaker damage.
        * **NORMAL**: normal attack speed, average damage.
        * **HEAVY**: very slow attack speed, massive damage.
    * **Attack types**
        * Sweeping: Charges up power for a swinging blow.
        * Shockwave: Depending on the material, the maces can destroy blocks in line of sight, for the cost of a heavy
          chunk of durability with a cooldown added on top.
    * The current recipe (is subject to change in the future), where S - Stick, M - Material

![Mace Recipe](https://raw.githubusercontent.com/sokratis12GR/ArmorPlus/1.16.3/mace_recipe.png "Mace Recipe")

* **Added** the first **config** entries.
    * **Ore Lava/Frost Crystal** world generation is now toggleable. You can enable/disable it. (Of course with this
      being the first config, options will definitely change over time)
    * **Ore Lava/Frost Crystal** world generation is now configurable, the vein size of each variant, the original Y
      offset and range they can spawn in.
* **Added** a new **Recipe API** for easier creation of crafting shaped/shapeless and/or smithing recipes.
    * Rewrote how all the recipes are being added/changed/generated so expect some recipes to be missing/changed.
    * Recipes will be changing periodically until they reach a final "satisfying" state.
    * I'll include all the changes in a guide/recipe book or something in game in a later version
* **Changed** the (Guardian, Super Star and Ender Dragon) swords, battle axes, pickaxes and bows to have a base item as
  a result of their recipe after which you need to add souls to them in the smithing table.
* **Added** new items:
    * Guardian, Super Star, Ender Dragon bases for the following tools/weapons: swords, battle axes, pickaxes and bows.
    * **Obsidian Stick**, crafted by using 2 obsidian blocks (shapeless recipe). Used for the new recipes of high tier
      items.
* **Fixed** bows not having a pulling animation with **new** textures! **Fixes #242**
* **Added** some flavour text to some items to hopefully help indicate how to use them.

ArmorPlus 1.16.3-16.1.1 (BETA)
----------------------------

**QUICK-FIX**: Fixed Version mismatching, recipe mismatching.

ArmorPlus 1.16.3-16.1.0 (BETA)
----------------------------

Keep In Mind that this is still beta, so some stuff might not have textures, others might crash etc. PLEASE REPORT ANY
ISSUES AT THE GITHUB ISSUES TRACKER: [ARMORPLUS GITHUB ISSUE TRACKER](https://github.com/sokratis12GR/ArmorPlus/issues)

* **Added support for 1.16.4, Requires TheDragonLib-1.16.3-1.1.0 or later**
* Added 2 new ore variations for the Lava & Frost ores (Stone & Obsidian)
    - Lava/Frost Crystals can be found in the world, from **Y: 0 up to Y: 20**, their rarity varies to their variation.
    - **Lava Crystals** **cannot** be found in frozen, icy, snowy or ocean biomes.
    - **Frost Crystals** can **only** be found in frozen, icy, snow, taiga or extreme hills biomes.
    - Updated their textures to more closely match the lava/frost crystals.
    - All 3 variants can be found in the Overworld.
    - Stone variants Y:12 up to Y:20
    - Obsidian variants Y:6 up to Y:10
    - Compressed Obsidian variants Y:0 up to Y:4
* Added back enhanced vanilla armors.
    - Enhanced is no longer an enchantment.
    - Added Enhanced Materials (Enhanced: Chainmail/Iron/Gold/Diamond)
    - Enhanced Materials are can be used to craft the enhanced armors (mirroring the vanilla recipes)
    - Enhanced Netherite armor can be obtained by combining netherite armor in the smithing table with
      an `Enhanced: Netherite Ingot`
* Removed a lot of redundant localization.
* Added new items (Super Star, Guardian, Ender Dragon, Slayer) bases, they need to be combined with the soul of their
  respective mobs in order to give you the complete item
    - You combine the base + the corresponding soul in a smithing table to get the expected complete armor piece
    - Recipes: `[Material Piece Base]` + `[Boss Soul <Mob>]` = `Material Piece`
* Changed the Super Star, Guardian, Ender Dragon crafting table recipe outcomes to give a base item instead of the full
  item.
* Added Boss Souls (Elder Guardian, Wither Boss, Ender Dragon)
    - Added servant/minion souls (Guardian, Wither Skeleton, Enderman, Blaze)
    - Added "pity" recipe for boss souls which require 4x lesser souls + 1 corresponding material + 4x corresponding
      blocks
    - (once a soul has been obtained the recipes will be unlocked and can be viewed on the recipe book)
* Added recipes for the arrows (8x arrows + 1 of the materials = 8 material arrows)
* Added a new ability `WATER_WEAKNESS` and assigned it to the infused lava armor, while you have the full set equipped
  and go into water, the armor will gradually lose durability
* From now on the `<build>` part (the last part) of the mod's version, will only increase if there were any internal
  issues with the file and will reset on every `<mcversion>-<major>.<minor>.<patch>` update
* Added a way to infuse a **lava crystal** into an **infused lava crystal** by **throwing** them in **lava**.
* Removed the particles from the effects that the armors provide
* Added bows back (their durability has changed), now its **(1/2)** of the material durability
    - **Coal**: 30 | **Lapis**: 125 | **Redstone**: 125 | **Emerald**: 780 | **Infused Lava**: 1500 | **Obsidian**: 2100
    - **Skeletal King's**: 3000 | **Elder's Force**: 3000 | **Rifting Light**: 3000
* Fixed ArmorPlus arrow entities causing crashes.
* Fixed Skeletal King, Witherlings causing crashes
    - Removed Wither Minions (WIP, Projectiles)

ArmorPlus 1.16.3-16.0.1.2 (BETA)
----------------------------

* Fixed servers crashing on startup.
* Fixed emerald weapon/armor temporary recipes.
* Fixed some more recipes being broken.
