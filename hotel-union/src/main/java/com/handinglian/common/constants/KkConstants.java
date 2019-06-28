package com.handinglian.common.constants;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/4 11:32
 */
public class KkConstants {
    //控客接口的key
    //绑定中控主机
    public static final String BINDING_HOST = "binding.host";
    //解绑中控主机
    public static final String UNBINDING_HOST = "unbinding.host";
    //获取中控主机基本信息
    public static final String GET_HOST_BASEINFO = "get.host.baseinfo";
    //打开网关通道
    public static final String OPEN_NETWORK = "open.network";
    //查询中控主机内设备列表
    public static final String GET_DEVICE_LIST = "get.device.list";
    //查询中控主机内节点列表
    public static final String GET_NODE_LIST = "get.node.list";
    //删除设备
    public static final String DELETE_DEVICE = "delete.device";
    //删除zigbee设备
    public static final String DEL_ZIGBEEN_NODE = "DelZigbeeNode";
    //非zigbee设备删除
    public static final String DEL_DEVICE = "DelDevice";





    //网关设置
    public static final String NETWORK_CONFIG = "network.config";
    //查询网关白名单运行状态
    public static final String GET_GW_WORKMODE = "GetGwWorkmode";
    //查询网关白名单运行状态
    public static final String SET_GW_WORKMODE = "SetGwWorkmode";
    //查询网关白名单列表
    public static final String GET_GW_WHITELIST = "GetGwWhitelist";
    //添加网关白名单列表
    public static final String ADD_GW_WHITELIST = "AddGwWhiteList";
    //删除网关白名单列表
    public static final String DEL_GW_WHITE_LIST = "DelGwWhiteList";


    //控客参数
    //中控主机id
    public static final String CCU_ID = "{ccuId}";
    //网关id
    public static final String GW_ID = "{gwId}";
}
