ArmorPlus 1.16.3-16.2.0 (BETA)
----------------------------

* **GeckoLib 3** is now a **required** dependency
    - What does this mean? Well now, modeling and animation will be much easier. Entities, armors, weapons and even some
      items will get new models/animations.
* The obsidian & stone variants of lava crystal and frost crystal ores should now drop the correct crystal. **Fixes #240 (1/2)**
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
    
|       |   M   |   M   |
|  ---  |  ---  |  ---  |
|       | **S** | **M** |
| **S** |       |       |

* **Added** the first **config** entries.
    * **Ore Lava/Frost Crystal** world generation is now toggleable. You can enable/disable it. (Of course with this
      being the first config, options will definitely change over time)
    * **Ore Lava/Frost Crystal** world generation is now configurable, the vein size of each variant, the original Y
      offset and range they can spawn in.
* **Added** a new **Recipe API** for easier creation of crafting shaped/shapeless and/or smithing recipes.
    * Rewrote how all the recipes are being added/changed/generated so expect some recipes to be missing/changed.
    * Recipes will be changing periodically until they reach a final "satisfying" state.
    * I'll include all the changes in a guide/recipe book or something in game in a later version
* **Changed** the (Guardian, Super Star and Ender Dragon) swords, battle axes, pickaxes and bows to have a base item as a
  result of their recipe after which you need to add souls to them in the smithing table.
* **Added** new items:
    * Guardian, Super Star, Ender Dragon bases for the following tools/weapons: swords, battle axes, pickaxes and bows.
    * **Obsidian Stick**, crafted by using 2 obsidian blocks (shapeless recipe). Used for the new recipes of high tier items.
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
