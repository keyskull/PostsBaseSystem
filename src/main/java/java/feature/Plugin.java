package java.feature;

import feature.Additional;
import feature.additional.PluginInfo;

import java.util.ArrayList;

/**
 * Created by Cullen Lee on 2016/10/14.
 */

interface _Plugin extends Additional {

    void init();

    void disable();

    String getDescription();
}

public abstract class Plugin implements _Plugin {

    protected PluginInfo pluginInfo() {
        return new PluginInfo(this.getClass().getName(), "", null, new ArrayList<>());
    }

    /**
     * @Override this value to support your interface for any other Plugin ues it.
     */
    protected Api getApi() {
        return new Api() {
            @Override
            PluginInfo getPluginInfo() {
                return Plugin.this.pluginInfo();
            }
        };
    }
}


