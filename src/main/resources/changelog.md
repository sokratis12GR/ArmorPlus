# 26.1.2 - 22.2.0

### New Equipment
- **Shovels:** every tool material now has its own shovel (Coal through Slayer, plus the Chicken, Slime, Frost, Frost-Lava, Tinkers' Construct and Enhanced vanilla sets), each with its own texture and on-hit effects.
  - Shovels use the same `weaponBuffs` config lists as the other tools and can be toggled in the item availability config.
  - Shovels can be enchanted like the other mining tools.
  - *"I am a dwarf and I'm digging a hole, diggy diggy hole!"* Now you can be too.
  - Matching crafting recipes were added: material over two sticks (obsidian sticks for the top tiers).
- **Boss shovels:** Guardian, Super Star, Ender Dragon, Warden and Slayer shovels are made like the rest of their sets: craft a soulless shovel base, then upgrade it with the boss soul (or Warden Heart / Soul: Slayer) at a smithing table.
  - The Slayer shovel base is crafted from the Super Star, Ender Dragon and Guardian shovel bases, like the other Slayer tools.

### Config & Dependencies
- **TheDragonLib 1.2.1 or newer is now required.** The config registration helper and the config screen pickers (searchable list pickers and their screens) now live in TheDragonLib and are shared with ArmorPlus, so update TheDragonLib alongside ArmorPlus.
- The in-game Configuration screen now uses the shared TheDragonLib pickers. Config options and file format are unchanged, so existing configs keep working.
- Added translations for the picker's "Add from List...", unavailable and manual entry texts.
