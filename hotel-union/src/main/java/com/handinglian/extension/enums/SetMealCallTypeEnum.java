package com.handinglian.extension.enums;

public enum SetMealCallTypeEnum {
    UNKNOWN(0,""),
    LOCAL_FIXED_PHONE(1,"本地固话"),
    LOCAL_MOBILE_PHONE(2,"本地手机"),
    LONG_DISTANCE_PHONE(3,"长途固话"),
    HONGKONG_AOMEN_TAIWAN(4,"香港澳门台湾"),
    AMERICA_CANADA(5,"美国加大那"),
    UK_FRANCE(6,"英法等"),
    OTHER(7,"其他");

    public static SetMealCallTypeEnum valueOfOrdinal(Integer ordinal) {
        return ordinal == null || ordinal >= values().length ? UNKNOWN : values()[ordinal];
    }
    public static SetMealCallTypeEnum valueOfDesc(String desc) {
        for(SetMealCallTypeEnum setMealCallTypeEnum: SetMealCallTypeEnum.values()){
            if(desc.equals(setMealCallTypeEnum.getDescription())){
                return setMealCallTypeEnum;
            }
        }
        return UNKNOWN;
    }
    public static SetMealCallTypeEnum valueOfKey(Integer key){
        for(SetMealCallTypeEnum setMealCallTypeEnum : SetMealCallTypeEnum .values()){
            if(setMealCallTypeEnum .getKey()==key){
                return setMealCallTypeEnum;
            }
        }
        return UNKNOWN;
    }
    private Integer key;
    private String description;
    public Integer getKey() {
        return key;
    }
    SetMealCallTypeEnum(Integer key, String description) {
        this.key = key;
        this.description = description;
    }
    SetMealCallTypeEnum() {
    }
    public void setKey(Integer key) {
        this.key = key;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description= description;
    }
}
