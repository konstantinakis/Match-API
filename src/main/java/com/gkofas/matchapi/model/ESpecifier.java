package com.gkofas.matchapi.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration with all the available specifier values.
 */
public enum ESpecifier {
    TIE("X"),
    FIRST_TEAM("1"),
    SECOND_TEAM("2"),
    EMPTY("");

    private static final Map<String,ESpecifier> ENUM_MAP = createEnumMap();

    private final String specifierValue;

    /**
     * constructor.
     */
    ESpecifier(String specifier) {
        specifierValue = specifier;
    }

    /**
     * Returns the string value of this {@link ESpecifier}.
     *
     * @return  the string value of this {@link ESpecifier}.
     */
    @Override
    public String toString() {
        return specifierValue;
    }

    /**
     * Returns the equivalent {@link ESpecifier} for the given {@code text}.
     * Note: It will never return null. If the text is irrelevant the return value will be {@link #EMPTY}
     *
     * @param text  a string value.
     * @return      a {@link ESpecifier} instance.
     */
    public static ESpecifier getESpecifierFrom(String text) {
        ESpecifier specifier = ENUM_MAP.get(text);
        if (specifier == null) {
            specifier = ESpecifier.EMPTY;
        }
        return specifier;
    }

    /*
     * creates the map of the Enum and its text values.
     */
    private static Map<String, ESpecifier> createEnumMap() {
        Map<String,ESpecifier> map = new HashMap<>();
        for (ESpecifier instance : ESpecifier.values()) {
            map.put(instance.toString(),instance);
        }
        return Collections.unmodifiableMap(map);
    }
}
