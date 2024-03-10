package com.ndz.app.utils;

import com.ndz.app.dto.convert.ExcelToAdministrativeUnit;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/9/2024
    github: https://github.com/NDZwei
*/
public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static String getStringCellValue(FormulaEvaluator evaluator, Cell cell) {
        if(cell.getCellType() != null) {
            switch (cell.getCellType()) {
                case FORMULA:
                    try {
                        switch (evaluator.evaluateFormulaCell(cell)) {
                            case NUMERIC:
                                return String.valueOf(cell.getNumericCellValue());
                            case STRING:
                                return cell.getStringCellValue();
                            default:
                                return null;
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        return null;
                    }
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case STRING:
                    return cell.getStringCellValue();
                default:
                    return null;
            }
        }
        return null;
    }

    public static List<ExcelToAdministrativeUnit> readDataFromExcel(Workbook wb) {
        try {
            List<ExcelToAdministrativeUnit> result = new ArrayList<>();
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = wb.getSheetAt(0);
            int rowIndex = 1;
            int lastRowNumber = sheet.getLastRowNum();
            Row row = null;
            Cell cell = null;
            while (rowIndex <= lastRowNumber) {
                row = sheet.getRow(rowIndex);
                if(row != null) {
                    ExcelToAdministrativeUnit dto = new ExcelToAdministrativeUnit();
                    int cellColum = 0;
                    cell = row.getCell(cellColum++);
                    if(cell != null) {
                        dto.setProvinceName(getStringCellValue(evaluator, cell));
                    }
                    cell = row.getCell(cellColum++);
                    if(cell != null) {
                        dto.setProvinceCode(getStringCellValue(evaluator, cell));
                    }
                    cell = row.getCell(cellColum++);
                    if(cell != null) {
                        dto.setDistrictName(getStringCellValue(evaluator, cell));
                    }
                    cell = row.getCell(cellColum++);
                    if(cell != null) {
                        dto.setDistrictCode(getStringCellValue(evaluator, cell));
                    }
                    cell = row.getCell(cellColum++);
                    if(cell != null) {
                        dto.setCommuneName(getStringCellValue(evaluator, cell));
                    }
                    cell = row.getCell(cellColum);
                    if(cell != null) {
                        dto.setCommuneCode(getStringCellValue(evaluator, cell));
                    }
                    result.add(dto);
                }
                rowIndex++;
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
