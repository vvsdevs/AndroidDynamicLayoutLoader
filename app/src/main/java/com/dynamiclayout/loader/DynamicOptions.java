package com.dynamiclayout.loader;

import java.util.Arrays;
import java.util.EnumSet;

public class DynamicOptions {

    /**
     * Options that can be attached to Dynamic object
     */
    public enum Option {
        /**
         * Force checking cache before fetching layout from server
         */
        ONLY_CACHE,

        /**
         * Fetch layout constantly
         * Note: Pause between server requests is 30 seconds or use setAsyncPause(long millis)
         */
        NON_STOP
    }

    private EnumSet<Option> options;

    DynamicOptions(Option ... options) {
        this.options = EnumSet.noneOf(Option.class);
        this.options.addAll(Arrays.asList(options));
    }

    public boolean isEnabled(Option option) {
        return options.contains(option);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}