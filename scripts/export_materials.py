"""Exports every material's armor/weapon effects (default config) and recipes to docs/materials.json.

Effects are parsed from ArmorPlusConfig.java, recipes from the datagen output in src/generated/server.
"""
import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
CONFIG = ROOT / "src/main/java/com/sofodev/armorplus/config/ArmorPlusConfig.java"
DATA = ROOT / "src/generated/server/data"
OUT = ROOT / "docs/materials.json"

ARMOR = ["helmet", "chestplate", "leggings", "boots"]
WEAPONS = ["battle_axe", "pickaxe", "sword", "bow", "mace"]
# config section -> prefix of its item ids (the enhanced vanilla sets are named differently)
PREFIX = {"enhanced_chainmail": "chainmail", "enhanced_golden": "golden", "enhanced_copper": "copper",
          "enhanced_iron": "iron", "enhanced_diamond": "diamond", "enhanced_netherite": "netherite"}
# enhanced material items (enhanced_gold, ...) -> the set they belong to
ENHANCED_ITEM = {"chainmail": "enhanced_chainmail", "gold": "enhanced_golden", "copper": "enhanced_copper",
                 "iron": "enhanced_iron", "diamond": "enhanced_diamond", "netherite": "enhanced_netherite"}


def parse_effect(entry):
    parts = entry.split("|")
    out = {"id": parts[0]}
    if len(parts) > 1:
        out["level"] = int(parts[1])
    if len(parts) > 2:
        out["seconds"] = int(parts[2])
    if parts[0].startswith("armorplus:"):
        out["ability"] = True
    return out


def split_args(text):
    """Splits on top-level commas, ignoring commas inside parentheses and strings."""
    args, depth, cur, in_str = [], 0, "", False
    for ch in text:
        if ch == '"':
            in_str = not in_str
        if not in_str:
            if ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
            elif ch == "," and depth == 0:
                args.append(cur.strip())
                cur = ""
                continue
        cur += ch
    args.append(cur.strip())
    return args


def parse_config():
    src = CONFIG.read_text(encoding="utf-8")
    materials = {}
    for m in re.finditer(r'MaterialConfig\.create\("(\w+)",\s*builder,', src):
        start = m.end()
        depth, i = 1, start
        while depth:
            depth += {"(": 1, ")": -1}.get(src[i], 0)
            i += 1
        armor, weapon = split_args(src[start:i - 1])[:2]
        listed = lambda a: None if a == "null" else [parse_effect(s) for s in re.findall(r'"([^"]+)"', a)]
        materials[m.group(1)] = {"armor": listed(armor), "weapon": listed(weapon)}
    return materials


def compact(recipe, rid):
    out = {"id": rid, "type": recipe["type"].removeprefix("minecraft:")}
    for k in ("pattern", "key", "ingredients", "template", "base", "addition", "ingredient", "experience", "cookingtime"):
        if k in recipe:
            out[k] = recipe[k]
    result = recipe["result"]
    out["count"] = result.get("count", 1) if isinstance(result, dict) else 1
    return out


def main():
    effects = parse_config()
    prefixes = {PREFIX.get(n, n): n for n in effects}
    ordered = sorted(prefixes, key=len, reverse=True)

    def material_of(item):
        if item.startswith("enhanced_") and item[9:] in ENHANCED_ITEM:
            return ENHANCED_ITEM[item[9:]], "material"
        for p in ordered:
            if item.startswith(p + "_"):
                kind = item[len(p) + 1:]
                if kind in ARMOR:
                    return prefixes[p], "armor:" + kind
                if kind in WEAPONS:
                    return prefixes[p], "weapon:" + kind
                return prefixes[p], "material"
        return None, "misc"

    result = {}
    for name, e in effects.items():
        result[name] = {"armor": {"fullSetEffects": e["armor"], "pieces": {}},
                        "weapons": {"onHitEffects": e["weapon"], "items": {}},
                        "materialRecipes": {}}
    misc = {}
    count = 0
    for path in sorted(DATA.glob("*/recipe/**/*.json")):
        recipe = json.loads(path.read_text(encoding="utf-8"))
        namespace = path.relative_to(DATA).parts[0]
        rid = f"{namespace}:{path.relative_to(DATA / namespace / 'recipe').with_suffix('').as_posix()}"
        if "result" not in recipe:  # special recipes (e.g. minecraft:repair_item) have no result
            count += 1
            misc.setdefault(rid, []).append({"id": rid, "type": recipe["type"].removeprefix("minecraft:")})
            continue
        item = recipe["result"]["id"].split(":")[1]
        mat, kind = material_of(item)
        count += 1
        if mat is None:
            misc.setdefault(item, []).append(compact(recipe, rid))
            continue
        side, _, piece = kind.partition(":")
        if side == "armor":
            target = result[mat]["armor"]["pieces"]
        elif side == "weapon":
            target = result[mat]["weapons"]["items"]
        else:
            target = result[mat]["materialRecipes"]
        target.setdefault(piece or item, []).append(compact(recipe, rid))
    # the enhanced vanilla netherite armor has smithing recipes whose result is plain 'netherite_*'; already covered above
    doc = {"_comment": "Generated by scripts/export_materials.py from the default config and datagen recipes. Every recipe has its id. Effect levels are as written in the config (1 = level I); 'seconds' is the duration.",
           "recipeCount": count, "materials": result, "otherRecipes": misc}
    OUT.parent.mkdir(exist_ok=True)
    OUT.write_text(json.dumps(doc, indent=2) + "\n", encoding="utf-8")
    print(f"{len(result)} materials, {count} recipes, {len(misc)} other -> {OUT}")
    print("other:", sorted(misc))


main()
