package com.sofodev.armorplus.data.recipe;

import com.sofodev.armorplus.utils.DataUtils;
import net.minecraft.util.IItemProvider;

import java.util.Optional;

public class Result {
    private String path = "";
    private String group;
    private IItemProvider object;
    private int count;
    private String prefix = "";
    private String suffix = "";

    public Result(IItemProvider object, int count) {
        this.object = object;
        this.count = count;
        this.group = DataUtils.getPath(object);
    }

    public Result(IItemProvider object) {
        this(object, 1);
    }

    public static Result build(IItemProvider object, int count) {
        return new Result(object, count);
    }

    public static Result build(IItemProvider object, int count, String group) {
        return new Result(object, count).setGroup(group);
    }

    public static Result build(IItemProvider object, int count, String group, String path) {
        return new Result(object, count).setGroup(group).setPath(path);
    }

    public static Result build(IItemProvider object) {
        return new Result(object);
    }

    public static Result build(IItemProvider object, String group) {
        return new Result(object).setGroup(group);
    }

    public static Result build(IItemProvider object, String group, String path) {
        return new Result(object).setGroup(group).setPath(path);
    }

    public Result setPath(String path) {
        this.path = path;
        return this;
    }

    public Result setGroup(String group) {
        this.group = group;
        return this;
    }

    public Result setObject(IItemProvider object) {
        this.object = object;
        return this;
    }

    public Result setCount(int count) {
        this.count = count;
        return this;
    }

    public Result setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Result setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public Optional<String> getPath() {
        if (path == null || path.equals("/")) return Optional.empty();
        if (path.contains("/")) {
            String trimmed = path.replace("/", "").trim();
            return Optional.of(trimmed + "/");
        }
        return Optional.of(path + "/");
    }

    public Optional<String> getGroup() {
        return Optional.ofNullable(group);
    }

    public int getCount() {
        return count;
    }

    public IItemProvider getObject() {
        return object;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}