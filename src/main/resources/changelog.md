# 26.1.2 - 22.1.0

### New Materials
- Added Pig Iron Ingots, Cobalt Ingots, Ardite Ingots, Knight Slime Crystals and Manyullyn Ingots (inspired by Tinkers' Construct), each with its own texture.
- Added the Warden Heart, dropped by every Warden on death (no Soul Stealer required).
- Chainmail is now craftable: 1 Iron Ingot + 3 Iron Nuggets makes 4 Chainmail.

### New Equipment
- **Vanilla-inspired sets:** Amethyst, Quartz, Glowstone and Honey, each with armor, sword, battle axe, pickaxe, mace, bow and arrow.
- **Warden boss set:** armor, sword, battle axe, pickaxe, bow, mace and arrow. The Warden arrow inflicts Darkness on hit.
  - Upgrading the base pieces costs 1 Warden Heart per item, so a full armor set takes 4 hearts.
- **Slayer is now harder to get**: Soul: Slayer each now also require a Warden Heart.
- **Tinkers' Construct armors** (Ardite, Cobalt, Knight Slime, Pig Iron and Manyullyn) and their materials are now craftable without Tinkers' Construct installed.
- **Weapon parity:** every armor set now has a matching sword, battle axe, pickaxe, bow and mace: Chicken, Slime, Frost, Frost-Lava, the Tinkers' Construct sets (Ardite, Cobalt, Knight Slime, Pig Iron, Manyullyn) and the Enhanced vanilla sets (Chainmail, Golden, Copper, Iron, Diamond, Netherite).
  - Each has its own color scheme (Enhanced sets get a warm glowstone highlight) and its own on-hit effects, e.g. Frost slows and tires, Ardite and Frost-Lava ignite, Manyullyn withers and ignites, Chicken lifts targets into the air.
  - The on-hit effects use the usual `weaponBuffs` config lists. The Enhanced weapons have their own `enhanced_chainmail`, `enhanced_golden`, `enhanced_copper`, `enhanced_iron`, `enhanced_diamond` and `enhanced_netherite` config sections.
  - Matching recipes and advancements were added.

### New Effects
- **Sculk Immunity:** the wearer doesn't trigger sculk sensors or shriekers.
- **Warden Pacification:** Wardens won't target or damage the wearer.
- **Darkness Immunity:** removes the Darkness effect from the wearer.
- **Mining Fatigue Immunity:** removes Mining Fatigue from the wearer, so it can't slow down mining (e.g. from Elder Guardians).
  - Added to the full set effects of the Guardian and Slayer armor.
  - Config id: `armorplus:mining_fatigue_immunity`.
- **Powdered Snow Immunity:** the wearer doesn't freeze in powdered snow: no freezing damage, no frozen screen overlay and no freeze slowdown.
  - Added to the full set effects of the Frost and Frost-Lava armor.
  - The wearer still sinks into powdered snow; only the freezing is prevented.
  - Config id: `armorplus:powdered_snow_immunity`.
- Both can be added to or removed from any material's `armorBuffs` list in the Configuration screen. Existing configs keep their old lists, so add them manually (or reset the config) to get them on the Guardian, Slayer, Frost and Frost-Lava armor.
- **Illuminate:** lights up the area around the wearer (Amethyst and Glowstone armor).
- The Slayer armor now has the Warden armor's effects as well.
- Tooltips with many effects are now shown as a compact, aligned grid of up to 3 columns, which shrinks to fit smaller screens.

### Config Overhaul
- Armor and weapon effects are now plain lists in each material's config section:
  - `armorBuffs`: full set effects, written as `"modid:effect|level"`, e.g. `"minecraft:haste|1"` for Haste I.
  - `weaponBuffs`: on-hit effects, written as `"modid:effect|level|seconds"`, e.g. `"minecraft:wither|2|4"`.
  - Any vanilla or modded effect works, as do ArmorPlus abilities such as `"armorplus:flight"`.
  - `disabledBuffs`: turn off individual effects (e.g. `"minecraft:night_vision"`) without removing them from the lists.
- Effects and abilities can be added from a searchable list in the Configuration screen.
- The Tinkers' Construct-inspired sets now have their own config sections.
- **Drop chances are configurable:** every mob and boss drop section now has `regularDropChance`, `regularDropMin` and `regularDropMax` for its materials and `soulDropChance` for its soul. The defaults match the previous behavior (1 in 4 for the lesser souls, guaranteed for bosses). Item tooltips show the configured values.
- **Note:** this replaces the old per-effect `enable…`/`amplifier…` options. Any customized effect settings from older configs are reset to the new defaults.

### Bug Fixes
- Maces now apply their material's hit effects and show them in their tooltip.
- Enhanced materials are no longer listed twice in the creative tabs.
- Materials, crystals, shards and souls now have informative tooltips: a short flavor line, plus brief, labeled details (Source, Craft, Drop, Smith, Use) with the key items highlighted (hold Shift for details). Drop chances shown there follow your config.
- Soul tooltips no longer create a temporary entity every frame just to display the mob's name.
