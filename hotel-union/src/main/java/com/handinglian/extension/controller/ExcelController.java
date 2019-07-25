package com.handinglian.extension.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.handinglian.extension.dto.CallRecordExcel;
import com.handinglian.extension.dto.MsgClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    @PostMapping("/exportCallRecord")
    public void exportCallRecord(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestBody List<CallRecordExcel> callRecordExcels) {
        ExportParams params = new ExportParams("通话记录", "通话记录", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(NormalExcelConstants.DATA_LIST, callRecordExcels);
        map.put(NormalExcelConstants.CLASS, MsgClient.class);
        map.put(NormalExcelConstants.PARAMS, params);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
