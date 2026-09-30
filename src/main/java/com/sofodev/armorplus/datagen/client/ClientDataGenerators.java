package com.sofodev.armorplus.datagen.client;

import net.minecraftforge.data.event.GatherDataEvent;

public final class ClientDataGenerators {

    private ClientDataGenerators() {
    }

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(output)
        );

        generator.addProvider(
                event.includeClient(),
                new ModEquipmentProvider(output)
        );

        generator.addProvider(
                event.includeClient(),
                new ModBlockModelProvider(output)
        );
    }
}