package com.handinglian.extension.enums;

public enum SetMealDefaultTypeEnum {
    NOT_DEFAULT(0,"非默认套餐"),
    DEFAULT(1,"默认套餐");

    public static SetMealDefaultTypeEnum valueOfOrdinal(Integer ordinal) {
        return ordinal == null || ordinal >= values().length ? null : values()[ordinal];
    }
    public static SetMealDefaultTypeEnum valueOfDesc(String desc) {
        for(SetMealDefaultTypeEnum setMealDefaultTypeEnum: SetMealDefaultTypeEnum.values()){
            if(desc.equals(setMealDefaultTypeEnum.getDescription())){
                return setMealDefaultTypeEnum;
            }
        }
        return null;
    }
    public static SetMealDefaultTypeEnum valueOfKey(Integer key){
        for(SetMealDefaultTypeEnum setMealDefaultTypeEnum : SetMealDefaultTypeEnum .values()){
            if(setMealDefaultTypeEnum .getKey()==key){
                return setMealDefaultTypeEnum;
            }
        }
        return null;
    }
    private Integer key;
    private String description;
    public Integer getKey() {
        return key;
    }
    SetMealDefaultTypeEnum(Integer key, String description) {
        this.key = key;
        this.description = description;
    }
    SetMealDefaultTypeEnum() {
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
