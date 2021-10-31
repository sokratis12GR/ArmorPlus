/*
The MIT License (MIT)
Copyright (c) 2020 Joseph Bettendorff aka "Commoble"
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
// DataFixerUpper is Copyright (c) Microsoft Corporation. All rights reserved. Licensed under the MIT license.
*/

package com.sofodev.armorplus.config;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.Config.Entry;
import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DataResult.PartialResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.RecordBuilder;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Config helper for automatically subscribing forge configs to config reload events as you build them. See DataBuddyExampleMod and ExampleServerConfig in the examplemod for usage examples.
 * <p><a href=https://github.com/Commoble/databuddy/blob/1.16.3/src/examplemod/java/commoble/databuddy/examplecontent/DataBuddyExampleMod.java> Example mod on github </a></p>
 * <p><a href=https://github.com/Commoble/databuddy/blob/1.16.3/src/examplemod/java/commoble/databuddy/examplecontent/ExampleServerConfig.java> Example config class on github </a></p>
 */
public class ConfigHelper {
    static final Logger LOGGER = LogManager.getLogger();

    /**
     * As with the other register method, but the contexts are assumed
     *
     * @param <T>           The class of your config implementation
     * @param configType    Forge config type:
     *                      <ul>
     *                      <li>SERVER configs are defined by the server and synced to clients
     *                      <li>COMMON configs are definable by both server and clients and not synced (they may have different values)
     *                      <li>CLIENT configs are defined by clients and not used on the server
     *                      </ul>
     * @param configBuilder Typically the constructor for your config class
     * @return An instance of your config class
     */
    public static <T> T register(
            final ModConfig.Type configType,
            final BiFunction<Builder, Subscriber, T> configBuilder) {
        return register(ModLoadingContext.get(), FMLJavaModLoadingContext.get(), configType, configBuilder);
    }

    /**
     * Call this in your @Mod class constructor.
     *
     * @param <T>           Your config class
     * @param modContext    mod context from ModLoadingContext.get()
     * @param fmlContext    mod context from FMLJavaModLoadingContext.get()
     * @param configType    Forge config type:
     *                      <ul>
     *                      <li>SERVER configs are defined by the server and synced to clients
     *                      <li>COMMON configs are definable by both server and clients and not synced (they may have different values)
     *                      <li>CLIENT configs are defined by clients and not used on the server
     *                      </ul>
     * @param configBuilder Typically the constructor for your config class
     * @return An instance of your config class
     */
    public static <T> T register(
            final ModLoadingContext modContext,
            final FMLJavaModLoadingContext fmlContext,
            final ModConfig.Type configType,
            final BiFunction<Builder, Subscriber, T> configBuilder) {
        final List<ConfigValueListener<?>> subscriptionList = new ArrayList<>();
        final org.apache.commons.lang3.tuple.Pair<T, ForgeConfigSpec> entry = new ForgeConfigSpec.Builder().configure(thisBuilder -> configBuilder.apply(thisBuilder, getSubscriber(subscriptionList)));
        final T config = entry.getLeft();
        final ForgeConfigSpec spec = entry.getRight();

        modContext.registerConfig(configType, spec);

        final Consumer<ModConfigEvent> configUpdate = event ->
        {
            if (event.getConfig().getSpec() == spec)
                for (ConfigValueListener<?> value : subscriptionList)
                    value.update();
        };

        fmlContext.getModEventBus().addListener(configUpdate);

        return config;
    }

    private static Subscriber getSubscriber(final List<ConfigValueListener<?>> list) {
        return new Subscriber(list);
    }

    /**
     * Subscriber instances are given to your config class's constructor
     **/
    public static class Subscriber {
        final List<ConfigValueListener<?>> list;

        Subscriber(final List<ConfigValueListener<?>> list) {
            this.list = list;
        }

        /**
         * Subscribe a config value to the config reload event. Use with a forge config builder to create the config values.
         *
         * @param <T>   The type of the value the config value is configuring
         * @param value The config value we are subscribing
         * @return A reload-sensitive wrapper around your config value. Use listener.get() to get the most up-to-date value.
         */
        public <T> ConfigValueListener<T> subscribe(final ConfigValue<T> value) {
            return ConfigValueListener.of(value, this.list);
        }

        /**
         * Subscribe a config value for a complex object type to the config reload event.
         * This is an experimental new feature and may not work correctly or at all with all types of objects.
         *
         * @param <T>           The type of the thing in the config we are making a listener for
         * @param builder       the forge config builder
         * @param name          The name of the field in your config that will hold objects of this type
         * @param codec         A Codec for de/serializing your object type. See drullkus's codec primer for guiding of the creation of these:<br>
         *                      <a href=https://gist.github.com/Drullkus/1bca3f2d7f048b1fe03be97c28f87910>https://gist.github.com/Drullkus/1bca3f2d7f048b1fe03be97c28f87910</a>
         * @param defaultObject The default instance of your config field. The given codec must be able to serialize this;
         *                      if it cannot, a RunTimeException will be intentionally thrown the first time the config attempts to load.
         *                      If the codec fails to deserialize the config field at a later time, an error message will be logged and this default instance will be used instead.
         * @return A reload-sensitive wrapper around your config object value. Use listener.get() to get the most up-to-date object.
         */
        public <T> ConfigObjectListener<T> subscribeObject(ForgeConfigSpec.Builder builder,
                                                           String name,
                                                           Codec<T> codec,
                                                           T defaultObject) {

            DataResult<Object> encodeResult = codec.encodeStart(TomlConfigOps.INSTANCE, defaultObject);
            Object encodedObject = encodeResult.getOrThrow(false, s -> LOGGER.error("Unable to encode default value: ", s));
            ConfigValueListener<Object> listener = this.subscribe(builder.define(name, encodedObject));
            return new ConfigObjectListener<>(listener, codec, defaultObject, encodedObject);
        }
    }

    /**
     * A config-reload-sensitive wrapper around your config value
     **/
    public static class ConfigValueListener<T> implements Supplier<T> {
        private final ConfigValue<T> configValue;
        private T value = null;

        private ConfigValueListener(final ConfigValue<T> configValue) {
            this.configValue = configValue;
            //this.value = configValue.get();
        }

        protected static <T> ConfigValueListener<T> of(final ConfigValue<T> configValue, final List<ConfigValueListener<?>> valueList) {
            final ConfigValueListener<T> value = new ConfigValueListener<T>(configValue);
            valueList.add(value);
            return value;
        }

        protected void update() {
            this.value = this.configValue.get();
        }

        @Override
        public T get() {
            if (this.value == null)
                this.update();
            return this.value;
        }
    }

    /**
     * A config-reload-sensitive wrapper around a config field for a complex object
     **/
    public static class ConfigObjectListener<T> implements Supplier<T> {
        final ConfigValueListener<Object> listener;
        final Codec<T> codec;
        Object cachedObject;
        T parsedObject;
        T defaultObject;

        private ConfigObjectListener(ConfigValueListener<Object> listener, Codec<T> codec, T defaultObject, Object encodedDefaultObject) {
            this.listener = listener;
            this.codec = codec;
            this.defaultObject = defaultObject;
            this.parsedObject = defaultObject;
            this.cachedObject = encodedDefaultObject;
        }

        @Override
        public T get() {
            Object freshObject = this.listener.get();
            if (!Objects.equals(this.cachedObject, freshObject)) {
                this.cachedObject = freshObject;
                this.parsedObject = this.getReparsedObject(freshObject);
            }
            return this.parsedObject;
        }

        private T getReparsedObject(Object obj) {
            DataResult<T> parseResult = this.codec.parse(TomlConfigOps.INSTANCE, obj);
            return parseResult.get().map(
                    result -> result,
                    failure ->
                    {
                        LOGGER.error("Config failure: Using default config value due to parsing error", failure.message());
                        return this.defaultObject;
                    });
        }
    }

    public static class TomlConfigOps implements DynamicOps<Object> {
        public static final TomlConfigOps INSTANCE = new TomlConfigOps();

        @Override
        public Object empty() {
            return NullObject.NULL_OBJECT;
        }

        @Override
        public <U> U convertTo(DynamicOps<U> outOps, Object input) {
            if (input instanceof Config) {
                return this.convertMap(outOps, input);
            }
            if (input instanceof Collection) {
                return this.convertList(outOps, input);
            }
            if (input == null || input instanceof NullObject) {
                return outOps.empty();
            }
            if (input instanceof Enum) {
                return outOps.createString(((Enum<?>) input).name());
            }
            if (input instanceof Temporal) {
                return outOps.createString(input.toString());
            }
            if (input instanceof String) {
                return outOps.createString((String) input);
            }
            if (input instanceof Boolean) {
                return outOps.createBoolean((Boolean) input);
            }
            if (input instanceof Number) {
                return outOps.createNumeric((Number) input);
            }
            throw new UnsupportedOperationException("TomlConfigOps was unable to convert toml value: " + input);
        }

        @Override
        public DataResult<Number> getNumberValue(Object input) {
            return input instanceof Number
                    ? DataResult.success((Number) input)
                    : DataResult.error("Not a number: " + input);
        }

        @Override
        public boolean compressMaps() {
            return false;
        }

        @Override
        public Object createNumeric(Number i) {
            return i;
        }

        @Override
        public DataResult<String> getStringValue(Object input) {
            if (input instanceof Config || input instanceof Collection) {
                return DataResult.error("Not a string: " + input);
            } else {
                return DataResult.success(String.valueOf(input));
            }
        }

        @Override
        public Object createString(String value) {
            return value;
        }

        @Override
        public DataResult<Object> mergeToList(Object list, Object value) {
            if (!(list instanceof Collection) && list != this.empty()) {
                return DataResult.error("mergeToList called with not a list: " + list, list);
            }
            final Collection<Object> result = new ArrayList<>();
            if (list != this.empty()) {
                Collection<? extends Object> listAsCollection = (Collection<? extends Object>) list;
                result.addAll(listAsCollection);
            }
            result.add(value);
            return DataResult.success(result);
        }

        @Override
        public DataResult<Object> mergeToMap(Object map, Object key, Object value) {
            if (!(map instanceof Config) && map != this.empty()) {
                return DataResult.error("mergeToMap called with not a map: " + map, map);
            }
            DataResult<String> stringResult = this.getStringValue(key);
            Optional<PartialResult<String>> badResult = stringResult.error();
            if (badResult.isPresent()) {
                return DataResult.error("key is not a string: " + key, map);
            }
            Optional<String> result = stringResult.result();
            return stringResult.flatMap(s -> {

                final Config output = TomlFormat.newConfig();
                if (map != this.empty()) {
                    Config oldConfig = (Config) map;
                    output.addAll(oldConfig);
                }
                output.add(s, value);
                return DataResult.success(output);
            });
        }

        @Override
        public DataResult<Stream<Pair<Object, Object>>> getMapValues(Object input) {
            if (!(input instanceof Config)) {
                return DataResult.error("Not a Config: " + input);
            }
            final Config config = (Config) input;
            return DataResult.success(config.entrySet().stream().map(entry -> Pair.of(entry.getKey(), entry.getValue())));
        }

        @Override
        public Object createMap(Stream<Pair<Object, Object>> map) {
            final Config result = TomlFormat.newConfig();
            map.forEach(p -> result.add(this.getStringValue(p.getFirst()).getOrThrow(false, s -> {
            }), p.getSecond()));
            return result;
        }

        @Override
        public DataResult<Stream<Object>> getStream(Object input) {
            if (input instanceof Collection) {
                Collection<Object> collection = (Collection<Object>) input;
                return DataResult.success(collection.stream());
            }
            return DataResult.error("Not a collection: " + input);
        }

        @Override
        public Object createList(Stream<Object> input) {
            return input.collect(Collectors.toList());
        }

        @Override
        public Object remove(Object input, String key) {
            if (input instanceof Config) {
                final Config result = TomlFormat.newConfig();
                final Config oldConfig = (Config) input;
                oldConfig.entrySet().stream()
                        .filter(entry -> !Objects.equals(entry.getKey(), key))
                        .forEach(entry -> result.add(entry.getKey(), entry.getValue()));
                return result;
            }
            return input;
        }

        @Override
        public String toString() {
            return "TOML";
        }

        @Override
        public RecordBuilder<Object> mapBuilder() {
            return DynamicOps.super.mapBuilder();
        }

        class TomlRecordBuilder extends RecordBuilder.AbstractStringBuilder<Object, Config> {

            protected TomlRecordBuilder() {
                super(TomlConfigOps.this);
            }

            @Override
            protected Config initBuilder() {
                return TomlFormat.newConfig();
            }

            @Override
            protected Config append(String key, Object value, Config builder) {
                builder.add(key, value);
                return builder;
            }

            @Override
            protected DataResult<Object> build(Config builder, Object prefix) {
                if (prefix == null || prefix instanceof NullObject) {
                    return DataResult.success(builder);
                }
                if (prefix instanceof Config) {
                    final Config result = TomlFormat.newConfig();
                    final Config oldConfig = (Config) prefix;
                    for (Entry entry : oldConfig.entrySet()) {
                        result.add(entry.getKey(), entry.getValue());
                    }
                    for (Entry entry : builder.entrySet()) {
                        result.add(entry.getKey(), entry.getValue());
                    }
                    return DataResult.success(result);
                }
                return DataResult.error("mergeToMap called with not a Config: " + prefix, prefix);
            }

        }
    }

}