# 1.21.1 - 23.1.0

Mobs can now use ArmorPlus gear and runes, and the long command lists are split into pages.

## Mobs
- **Mobs use ArmorPlus gear:** a mob wearing ArmorPlus armor or socketed runes gets its set and rune effects.
- **Mob flight:** mobs with the Flight effect fly after targets they can't reach on foot, and glide down instead of taking fall damage.
- **Mob ranged runes:** mobs can fire ranged runes (Seeker, Ricochet, Marked, Long Shot) at reduced strength. They target players and never other monsters.
- **Smarter targeting:** Seeker and Ricochet skip neutral mobs unless those are angry at you, and always count whatever you're fighting.

## Tools
- Tool tooltips now show a **While Held** section for material effects such as Infused Lava's Auto-Smelt.
- The Tool Effects screen now lists tool runes, and you can switch Infused Lava's Auto-Smelt off there.

## Commands
- `/armorplus help`, `/armorplus effects` and `/armorplus rune list` are now split into pages with clickable **[◀ Prev]** / **[Next ▶]** buttons.

## Configs
- New `mobs` section in `armorplus/effects.toml`: `mobsUseRuneEffects`, `mobRangedEffectiveness`, `mobFlight`, `mobFlightSpeed`, `mobEffectBlacklist` and `mobFlightBlacklist`.

## API Additions
- New `EffectScope`, `EffectScopes` and `EffectEntry` for checking and toggling a player's armor, tool, weapon and ranged effects.
