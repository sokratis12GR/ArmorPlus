/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.iface;

import com.google.common.base.Charsets;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class ResourceContentProvider implements ContentProvider {
    private final String resourceDomain;

    private final String basePath;

    public ResourceContentProvider(String resourceDomain, String basePath) {
        this.resourceDomain = resourceDomain;
        this.basePath = basePath;
    }

    public ResourceContentProvider(String resourceDomain) {
        this(resourceDomain, "");
    }

    @Override
    public Iterable<String> getContent(String path) {
        final ResourceLocation location = new ResourceLocation(resourceDomain, basePath + (path.startsWith("/") ? path.substring(1) : path));
        InputStream is = null;
        try {
            is = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charsets.UTF_8));
            final ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Throwable ignored) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
