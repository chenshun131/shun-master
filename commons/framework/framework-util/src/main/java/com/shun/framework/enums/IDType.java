package com.shun.framework.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/6 11:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public enum IDType implements BaseEnum<IDType, String> {

    ID("ID"),
    DIL("DIL"),
    DSL("DSL"),
    OCC("OCC"),
    PASSPORT("PASSPORT"),
    COO("COO"),
    HRP("HRP"),
    BL("BL"),
    ELSE("ELSE");

    private String code;

    private static Map<String, IDType> codeMap = new HashMap();

    private IDType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static IDType getByCode(String code) {
        if (codeMap.size() != 0) {
            return (IDType) codeMap.get(code);
        } else {
            IDType[] statusList = values();
            IDType[] var5 = statusList;
            int var4 = statusList.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                IDType status = var5[var3];
                codeMap.put(status.getCode(), status);
            }

            return (IDType) codeMap.get(code);
        }
    }

}
