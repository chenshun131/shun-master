package com.shun.framework.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/6 11:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public enum TerminalSource {
    PC,
    H5,
    IOS,
    Android;

    private static Map<String, TerminalSource> map = new HashMap();

    private TerminalSource() {
    }

    public static TerminalSource parse(String code) {
        if (map.size() != 0) {
            return (TerminalSource) map.get(code);
        } else {
            TerminalSource[] categoryList = values();
            TerminalSource[] var5 = categoryList;
            int var4 = categoryList.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                TerminalSource category = var5[var3];
                map.put(category.name(), category);
            }

            return (TerminalSource) map.get(code);
        }
    }

}
