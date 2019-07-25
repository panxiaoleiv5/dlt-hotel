package com.handinglian.extension.style;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author pxl
 * @description
 * @date 2019/3/27 10:33
 */
public class ExcelExportStylerBookImpl extends AbstractExcelExportStyler implements IExcelExportStyler {

    public ExcelExportStylerBookImpl(Workbook workbook) {
        super.createStyles(workbook);
    }

    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setFont(font);
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        return headStyle;
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        titleStyle.setFont(font);
        titleStyle.setWrapText(true);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        return titleStyle;
    }

    @Override
    public CellStyle getStyles(boolean noneStyler, ExcelExportEntity entity) {
        Font font = workbook.createFont();
        font.setFontName("宋体");

        CellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setAlignment(CellStyle.ALIGN_CENTER);
        normalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        normalStyle.setFont(font);
        normalStyle.setWrapText(true);
        normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        return normalStyle;
    }
}
