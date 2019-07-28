package com.handinglian.system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class FileInfo implements Serializable {
    /**
    * 文件uuid
    */
    private String fileUuid;

    /**
    * 文件名称
    */
    private String fileName;

    /**
    * 文件大小
    */
    private String fileSize;

    /**
    * 相对路径
    */
    private String relativePath;

    /**
    * 创建时间
    */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}