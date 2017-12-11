package com.shun.framework.common;

/**
 * User: mew <p />
 * Time: 17/11/6 11:10  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public enum SystemModule {
    COMMONS("Commons", "00", "常量模块"),
    ORG("Org", "10", "组织架构模块"),
    MEMBER("Member", "20", "会员/用户模块"),
    CACHE("Cache", "30", "缓存模块"),
    FINANCE("Finance", "40", "金融模块"),
    MNS("Mns", "50", "消息模块"),
    UES("Ues", "60", "统一加密解密模块"),
    MANAGE("Manage", "70", "后台管理模块"),
    GOODS("Goods", "80", "商品库存模块"),
    WEB_SITE("WebSite", "90", "门户模块"),
    CMS("Cms", "A0", "内容发布模块"),
    EMARKETING("Emarketing", "B0", "市场营销模块"),
    TRADE("Trade", "C0", "交易模块"),
    VehicleIns("VehicleIns", "D0", "车险模块");

    private String code;

    private String no;

    private String name;

    private SystemModule(String code, String no, String name) {
        this.code = code;
        this.no = no;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

}
