package sokratis12GR.ArmorPlus;


import sokratis12GR.ArmorPlus.resources.ArmorPlusItems;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers(ArmorPlus ins)
	{
		ins.CoalArmor.registerRenderers();
		ins.LapisArmor.registerRenderers();
		ins.RedstoneArmor.registerRenderers();
		ins.EmeraldArmor.registerRenderers();
		ins.ObsidianArmor.registerRenderers();
		ins.LavaArmor.registerRenderers();
		ins.SuperStarArmor.registerRenderers();
		ins.EnderDragonArmor.registerRenderers();
		ins.GuardianArmor.registerRenderers();
		ArmorPlusItems.initModels();
	}
}
