ArmorPlus 1.16.3-16.1.0.0 (BETA)
----------------------------

Keep In Mind that this is still beta, so some stuff might not have textures, others might crash etc.
PLEASE REPORT ANY ISSUES AT THE GITHUB ISSUES TRACKER: [ARMORPLUS GITHUB ISSUE TRACKER](https://github.com/sokratis12GR/ArmorPlus/issues)

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
  - Enhanced Netherite armor can be obtained by combining netherite armor in the smithing table with an `Enhanced: Netherite Ingot` 
* Removed a lot of redundant localization.
* Added new items (Super Star, Guardian, Ender Dragon, Slayer) bases, they need to be combined with the soul of their respective mobs in order to give you the complete item
  - You combine the base + the corresponding soul in a smithing table to get the expected complete armor piece 
  - Recipes: `[Material Piece Base]` + `[Boss Soul <Mob>]` = `Material Piece` 
* Changed the Super Star, Guardian, Ender Dragon crafting table recipe outcomes to give a base item instead of the full item. 
* Added Boss Souls (Elder Guardian, Wither Boss, Ender Dragon)
  - Added servant/minion souls (Guardian, Wither Skeleton, Enderman, Blaze)
  - Added "pity" recipe for boss souls which require 4x lesser souls + 1 corresponding material + 4x corresponding blocks
  - (once a soul has been obtained the recipes will be unlocked and can be viewed on the recipe book)
* Added recipes for the arrows (8x arrows + 1 of the materials = 8 material arrows)
* Added a new ability `WATER_WEAKNESS` and assigned it to the infused lava armor, while you have the full set equipped and go into water, the armor will gradually lose durability
* From now on the `<build>` part (the last part) of the mod's version, will only increase if there were any internal issues with the file and will reset on every `<mcversion>-<major>.<minor>.<patch>` update
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
