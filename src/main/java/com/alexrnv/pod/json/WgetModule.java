package com.alexrnv.pod.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.MultiMap;

/**
 * Date: 9/2/2015
 * Time: 3:36 PM
 *
 * Author: Alex
 */
public class WgetModule extends SimpleModule {
    public WgetModule() {
        super();
        addSerializer(MultiMap.class, new MultiMapSerializer());
        addDeserializer(MultiMap.class, new MultiMapDeserializer());
    }
}

