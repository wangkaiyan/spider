package com.spider.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by qd on 2016/4/10.
 */
public class MetalTypeUtil {

    public static final BiMap<String,Integer> METAL_TYPE = HashBiMap.create();
    static {
        METAL_TYPE.put("钢", 24); //成人
        METAL_TYPE.put("铜", 25); //成人
        METAL_TYPE.put("铝", 26); //成人
        METAL_TYPE.put("镍", 27); //成人
        METAL_TYPE.put("", -1); //成人
    }

}
