package com.handinglian.contentunion.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CentralHost implements Serializable {
    /**
     * 主键
     */
    private Integer centralHostId;

    /**
     * 主机位置
     */
    private String hostAddress;

    /**
     * 中控主机联网的本地ip地址
     */
    private String localIp;

    /**
     * 识别码
     */
    private String macIp;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 控客平台上的中控主机id
     */
    private String kkHostId;

    /**
     * 控客平台上的中控主机类别ID
     */
    private String productId;

    /**
     * 控客平台主机设备编号
     */
    private String deviceId;

    /**
     * 中控当前运行版本
     */
    private String curVersion;

    /**
     * 中控已下载版本，用于升级
     */
    private String downloadVersion;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer valid;

    private static final long serialVersionUID = 1L;
}