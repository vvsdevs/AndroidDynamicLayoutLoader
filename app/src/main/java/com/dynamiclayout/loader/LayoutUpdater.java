package com.dynamiclayout.loader;

import android.os.Looper;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

public final class LayoutUpdater {

    private static final String TAG = "Dynamic";

    private long asyncPause = 30 * 1000;
    private DynamicLayoutLoader loader;
    private DynamicOptions options;

    /**
     * Basic constructor (Not using cache)
     * @param res URL of directory where JSON layout file is located (for example, "https://ecloga.org/prjects/Dynamic") or JSON layout as a String
     * @param name JSON layout file name with or without extension (for example, "activity_main")
     * @param layout wrapper layout that will contain inflated layout from JSON file (for example, findViewById(R.id.mainLayout))
     * @throws DynamicException if any of passed parameters is null
     */
    public LayoutUpdater(String res, String name, ViewGroup layout) throws DynamicException {
        if(res == null || name == null || layout == null) {
            throw new DynamicException("Parameters cannot be null");
        }

        this.loader = new DynamicLayoutLoader(res, name, layout);
        this.options = new DynamicOptions();
    }

    /**
     * Attaches event listener to Dynamic object
     * @param listener listener for success and error events caused by network, storage, etc.
     * @return Dynamic object ready for initialization
     */
    public LayoutUpdater setListener(DynamicListener listener) {
        loader.setListener(listener);

        return this;
    }

    /**
     * Attaches options to Dynamic object
     * @param options options for Dynamic (for example, ONLY_CACHE)
     * @return Dynamic object ready for initialization
     */
    public LayoutUpdater setOptions(DynamicOptions.Option ... options) {
        this.options = new DynamicOptions(options);

        return this;
    }

    /**
     * Overrides default asyncPause (30 seconds)
     * Note: NON_STOP option must be enabled
     * @param millis asynchronous call pause in milliseconds
     * @return Dynamic object ready for initialization
     */
    public LayoutUpdater setAsyncPause(long millis) {
        this.asyncPause = millis;

        return this;
    }

    /**
     * Starts layout fetching from cache/server depending on provided options
     */
    public void initialize() {
        Util.log(TAG, "Device information: " + Device.getAllInfo());
        Util.log(TAG, "Dynamic options: " + options);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(Looper.myLooper() == null) {
                    Looper.prepare();
                }

                if(options.isEnabled(DynamicOptions.Option.ONLY_CACHE)) {
                    loader.loadLayoutFromCache();
                }else {
                    loader.loadLayoutWithoutCache();
                }

                if(!options.isEnabled(DynamicOptions.Option.NON_STOP)) {
                    this.cancel();
                }
            }
        }, 0, asyncPause);
    }
}