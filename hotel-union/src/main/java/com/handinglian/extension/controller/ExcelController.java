package com.handinglian.extension.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.apidoc.annotation.Api;
import com.handinglian.extension.dto.CallRecordExcel;
import com.handinglian.extension.dto.MsgClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api("报表导出")
@Controller
@RequestMapping("/excel")
public class ExcelController {
    /**
     * 通话记录报表导出
     */
    @PostMapping("/exportCallRecord")
    public void exportCallRecord(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestBody List<CallRecordExcel> callRecordExcelList) {
        ExportParams params = new ExportParams("通话记录", "通话记录", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(NormalExcelConstants.DATA_LIST, callRecordExcelList);
        map.put(NormalExcelConstants.CLASS, CallRecordExcel.class);
        map.put(NormalExcelConstants.PARAMS, params);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
