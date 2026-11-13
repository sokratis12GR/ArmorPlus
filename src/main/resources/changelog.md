# 26.1.2 - 23.1.0

Mobs can now use ArmorPlus armor and runes, Infused Lava's Auto-Smelt is a proper tool effect, long command output is split into pages, and the effect system is rebuilt so new kinds of effects are easy to add.

## New & Changed Features

### Mobs & Runes
- **Mobs use their armor's effects:** mobs wearing ArmorPlus armor or armor runes get its set and rune effects, refreshed once a second (`mobsUseRuneEffects`). Only mob effects apply: other abilities and instant effects stay with players.
- **Flying mobs:** mobs with Flight take off after targets they can't reach on foot or that are high above them, hover when close enough to fight, and glide down instead of taking fall damage (`mobFlight`, `mobFlightSpeed`).
- **Mob ranged runes:** mobs' Seeker, Ricochet, Marked and Long Shot are weaker than players' (`mobRangedEffectiveness`, 0.5 = half). Their Seeker and Ricochet arrows only go for players in Survival or Adventure.
- **Smarter Seeker and Ricochet:** player arrows ignore neutral mobs (e.g. calm Piglins) unless they're angry at you.
- Built to stay cheap on servers: a mob is only checked when its armor changes, and only mobs with Flight are steered every tick.

### Tool Effects
- **Infused Lava Auto-Smelt is now a tool effect:** Infused Lava tools list it under a new **While Held:** tooltip header, and it can be switched off in the Tool Effects screen (**H**) like the Auto-Smelt rune. The material's `enableWeaponEffects` option still turns it off for everyone.
- The Tool Effects screen now has a page for the held tool's own effects, next to its runes page.

### Commands
- **Pages:** `/armorplus effects`, `/armorplus rune list` and `/armorplus help` show 8 lines at a time, with clickable `[◀ Prev]` and `[Next ▶]` buttons.
  - `/armorplus effects page <page>` for your own effects, `/armorplus effects <player> [page]` for another player's (needs OP).
  - `/armorplus rune list [armor|weapon|tool|ranged] [page]` and `/armorplus help [page]`.
- A bare `/armorplus` or `/ap` now shows the help page.

### Fixes
- Removed a leftover Infused Lava smelting check that never did anything. Auto-smelting itself is unchanged.

## Configs
- **New `mobs` section in `armorplus/effects.toml`:** `mobsUseRuneEffects`, `mobRangedEffectiveness`, `mobFlight`, `mobFlightSpeed`, `mobEffectBlacklist` (mobs that never get armor effects) and `mobFlightBlacklist` (mobs that never fly). All of them can be edited in the config screen.
- Nothing else changes: existing config files keep working.

## API Changes
- **Effect scopes:** armor, tool, weapon and ranged effects are now all built the same way, as an `EffectScope` (`com.sofodev.armorplus.api`) registered in `EffectScopes`. Each entry is an `EffectEntry` with its effect, source, toggle key, piece count and on/off state.
  - New kinds of effects only need an `EffectScope` subclass, a disabled-effects attachment and, for a screen, an `EffectsScreen` subclass and a tab (`EffectsScreen.Tab.register`). Commands, toggles, syncing and the API pick it up automatically.
  - `ArmorPlusAPI.getEffectScopes()`, `hasEffect(scope, entity, buff)`, `getEffects(scope, entity)` and `getEffectEntries(scope, entity)`. The per-kind methods still work.
  - **⚠ Breaking for addons:** the static `active`, `has`, `entries`, `isDisabled`, `invalidate` and `clearCaches` methods of `ArmorEffects`, `ToolEffects`, `WeaponEffects` and `RangedEffects` are removed. Use `EffectScopes.ARMOR`, `TOOL`, `WEAPON` and `RANGED` instead.
- Tool materials can give effects while held through `IAPTool.getHeldBuffs()`.
- ⚠ The effect toggle packet now sends the scope's name, so clients and servers must both run 23.1.0.