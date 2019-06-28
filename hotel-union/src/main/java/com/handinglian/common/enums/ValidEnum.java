package com.handinglian.common.enums;

public enum ValidEnum {
    INVALID(0,"无效"),
    VALID(1,"有效");

    public static ValidEnum valueOfOrdinal(Integer ordinal) {
        return ordinal == null || ordinal >= values().length ? null : values()[ordinal];
    }
    public static ValidEnum valueOfDesc(String desc) {
        for(ValidEnum validEnum : ValidEnum.values()){
            if(desc.equals(validEnum.getDescription())){
                return validEnum;
            }
        }
        return null;
    }
    public static ValidEnum valueOfKey(Integer key){
        for(ValidEnum validEnum : ValidEnum.values()){
            if(validEnum.getKey()==key){
                return validEnum;
            }
        }
        return null;
    }
    private Integer key;
    private String description;
    public Integer getKey() {
        return key;
    }
    ValidEnum(Integer key, String description) {
        this.key = key;
        this.description = description;
    }
    ValidEnum() {
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
