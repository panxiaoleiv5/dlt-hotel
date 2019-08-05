package com.handinglian.contentunion.entity;

import lombok.Data;

@Data
public class ProductList {
    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private String productType;
}
