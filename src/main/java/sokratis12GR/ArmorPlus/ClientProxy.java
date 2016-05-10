package sokratis12GR.ArmorPlus;

import sokratis12GR.ArmorPlus.registry.ModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers(ArmorPlus ins) {
        ins.CoalArmor.registerRenderers();
        ins.LapisArmor.registerRenderers();
        ins.RedstoneArmor.registerRenderers();
        ins.EmeraldArmor.registerRenderers();
        ins.ObsidianArmor.registerRenderers();
        ins.LavaArmor.registerRenderers();
        ins.SuperStarArmor.registerRenderers();
        ins.EnderDragonArmor.registerRenderers();
        ins.GuardianArmor.registerRenderers();
        ins.TheUltimateArmor.registerRenderers();
        ins.RIArmor.registerRenderers();
        ins.RGArmor.registerRenderers();
        ins.RDArmor.registerRenderers();
        ins.RCArmor.registerRenderers();
        ins.CustomArmor.registerRenderers();
        ModItems.initModels();
    }
}
