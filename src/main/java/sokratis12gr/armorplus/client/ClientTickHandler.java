package sokratis12gr.armorplus.client;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.api.IClientTicker;
import sokratis12gr.armorplus.api.ObfuscatedNames;
import sokratis12gr.armorplus.api.util.ReflectionUtils;

@SideOnly(Side.CLIENT)
public class ClientTickHandler {

    public static Minecraft mc = FMLClientHandler.instance().getClient();

    public static final String DONATE_CAPE = "http://capes.sokratis12gr.tk/cape.png";
    public static final String THE_DRAGON_TEAM_CAPE = "http://capes.sokratis12gr.tk/thedragoncape.png";

    private Map<String, CapeBufferDownload> donateDownload = new HashMap<String, CapeBufferDownload>();
    private Map<String, CapeBufferDownload> thedragonteamDownload = new HashMap<String, CapeBufferDownload>();

    public static Set<IClientTicker> tickingSet = new HashSet<IClientTicker>();

    @SubscribeEvent
    public void onTick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            tickStart();
        }
    }

    public void tickStart() {

        if (!ArmorPlus.proxy.isPaused()) {
            for (Iterator<IClientTicker> iter = tickingSet.iterator(); iter.hasNext(); ) {
                IClientTicker ticker = iter.next();

                if (ticker.needsTicks()) {
                    ticker.clientTick();
                } else {
                    iter.remove();
                }
            }
        }

        if (mc.theWorld != null && !ArmorPlus.proxy.isPaused()) {
            for (EntityPlayer entityPlayer : mc.theWorld.playerEntities) {
                if (entityPlayer instanceof AbstractClientPlayer) {
                    AbstractClientPlayer player = (AbstractClientPlayer) entityPlayer;

                    if (StringUtils.stripControlCodes(player.getName()).equals("Moritz30")) {
                        CapeBufferDownload download = thedragonteamDownload.get(player.getName());

                        if (download == null) {
                            download = new CapeBufferDownload(player.getName(), THE_DRAGON_TEAM_CAPE);
                            thedragonteamDownload.put(player.getName(), download);

                            download.start();
                        } else {
                            if (!download.downloaded) {
                                continue;
                            }

                            setCape(player, download.getResourceLocation());
                        }
                    } else if (StringUtils.stripControlCodes(player.getName()).equals("sokratis12GR")) {
                        CapeBufferDownload download = thedragonteamDownload.get(player.getName());

                        if (download == null) {
                            download = new CapeBufferDownload(player.getName(), THE_DRAGON_TEAM_CAPE);
                            thedragonteamDownload.put(player.getName(), download);

                            download.start();
                        } else {
                            if (!download.downloaded) {
                                continue;
                            }

                            setCape(player, download.getResourceLocation());
                        }
                    } else if (ArmorPlus.donators.contains(StringUtils.stripControlCodes(player.getName()))) {
                        CapeBufferDownload download = donateDownload.get(player.getName());

                        if (download == null) {
                            download = new CapeBufferDownload(player.getName(), DONATE_CAPE);
                            donateDownload.put(player.getName(), download);

                            download.start();
                        } else {
                            if (!download.downloaded) {
                                continue;
                            }

                            setCape(player, download.getResourceLocation());
                        }
                    }
                }
            }
        }
    }


    public static void setCape(AbstractClientPlayer player, ResourceLocation cape) {
        try {
            Method m = ReflectionUtils.getPrivateMethod(AbstractClientPlayer.class, ObfuscatedNames.AbstractClientPlayer_getPlayerInfo);
            Object obj = m.invoke(player);

            if (obj instanceof NetworkPlayerInfo) {
                NetworkPlayerInfo info = (NetworkPlayerInfo) obj;
                Map<MinecraftProfileTexture.Type, ResourceLocation> map = (Map<MinecraftProfileTexture.Type, ResourceLocation>) ReflectionUtils.getPrivateValue(info, NetworkPlayerInfo.class, ObfuscatedNames.NetworkPlayerInfo_playerTextures);
                map.put(MinecraftProfileTexture.Type.CAPE, cape);
            }
        } catch (Exception e) {
        }
    }
}