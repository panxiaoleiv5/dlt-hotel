package com.handinglian.contentunion.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.contentunion.entity.CentralHost;
import org.dom4j.DocumentException;

import java.io.IOException;

public interface CentralHostService {
    int createCentralHost(String hostAddress, String macIp) throws IOException, DocumentException;

    /**
    * 激活中控主机，获取中控主机的基本信息并插入数据库
    * @author pxl
    * @param centralHostId
    * @return int
    * @date 2019/6/3 17:55
    */
    int activateCentralHost(Integer centralHostId) throws IOException, DocumentException;

    int deleteCentralHost(Integer centralHostId) throws IOException, DocumentException;

    CentralHost loadCentralHost(Integer centralHostId);

    int updateCentralHost(CentralHost centralHost);


    /**
    * 恢复删除的中控主机
    * @author pxl
    * @param macIp
    * @return int
    * @date 2019/6/3 17:56
    */
    int recoverCentralHost(String macIp) throws IOException, DocumentException;

    /**
    * 查询被删除的中控主机
    * @author pxl
    * @param macIp 识别码
    * @return com.handinglian.contentunion.entity.CentralHost
    * @date 2019/6/3 10:49
    */
    CentralHost loadInvalidCentralHost(String macIp);

    /**
    * 分页查询中控主机列表
    * @author pxl
    * @param pageIndex
    * @param pageSize
    * @return com.github.pagehelper.PageInfo<com.handinglian.contentunion.entity.CentralHost>
    * @date 2019/6/3 10:48
    */
    PageInfo<CentralHost> inquireCentralHostPageList(Integer pageIndex, Integer pageSize);

    /**
    * 组网
    * @author pxl
    * @param centralHostId
    * @return void
    * @date 2019/6/4 10:12
    */
    void uniteNetwork(Integer centralHostId) throws IOException, DocumentException;
}
