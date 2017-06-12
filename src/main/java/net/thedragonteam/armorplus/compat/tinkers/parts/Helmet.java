//package net.thedragonteam.armorplus.compat.tinkers.parts;

//public class Helmet extends APItemCore {
//
//    // helmetLeft, right, middle
//    public Helmet() {
//        this(APItemCore.helmetLeft(TiC.helmetLeft),
//                APItemCore.helmetMiddle(TiC.helmetMiddle),
//                APItemCore.helmetRight(TiC.helmetRight));
//    }
//
//    public Helmet(PartMaterialType... requiredComponents) {
//        super(requiredComponents);
//        this.addCategory(TiC.ARMOR);
//    }
//
//    @Override
//    public NBTTagCompound buildTag(List<Material> materials) {
//        return null;
//    }
//
//    @Override
//    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
//        return super.isValidArmor(stack, EntityEquipmentSlot.HEAD, entity);
//    }
//
//    @Override
//    public void getSubItems(@NotNull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
//        addDefaultSubItems(subItems);
//    }
//
//    protected void addDefaultSubItems(List<ItemStack> subItems, Material... fixedMaterials) {
//        for (Material head : TinkerRegistry.getAllMaterials()) {
//            List<Material> mats = IntStream.range(0, requiredComponents.length).mapToObj(i -> fixedMaterials.length > i && fixedMaterials[i] != null && requiredComponents[i].isValidMaterial(fixedMaterials[i]) ? fixedMaterials[i] : head).collect(Collectors.toCollection(() -> new ArrayList<>(requiredComponents.length)));
//
//            ItemStack tool = buildItem(mats);
//            // only valid ones
//            if (hasValidMaterials(tool)) {
//                subItems.add(tool);
//                if (!Config.listAllMaterials) break;
//            }
//        }
//    }
//}