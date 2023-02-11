package com.inetbanking.utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsUtil {


        private static XSSFSheet ExcelWSheet;

        private static XSSFWorkbook ExcelWBook;

        private static XSSFCell xCell;

        private static XSSFRow xRow;

        public static void main(String... args) {

            try {
                InputStream is = readInputStreamFromFile();

                XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
                XSSFSheet mySheet = myWorkBook.getSheetAt(0);
                ExcelWSheet = mySheet;

                System.out.println(getCellData(1, 0));
                System.out.println(getCellData(1, 1));

                is.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


        public static String getCellData(int RowNum, int ColNum) throws Exception{

            try {

                String cellData = "";

                xCell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                xCell.setCellType(CellType.STRING);
                cellData = xCell.getStringCellValue();

                //            switch (xCell.getCellTypeEnum()) {
                //            case STRING:
                //                //System.out.print(xCell.getStringCellValue() + "\t");
                //                cellData = xCell.getStringCellValue();
                //                break;
                //            case NUMERIC:
                //              cellData = String.valueOf(xCell.getNumericCellValue());
                //                //System.out.print(xCell.getNumericCellValue() + "\t");
                //                break;
                //            case BOOLEAN:
                //              cellData = String.valueOf(xCell.getBooleanCellValue());
                //                //System.out.print(xCell.getBooleanCellValue() + "\t");
                //                break;
                //
                //            default:
                //              cellData = "undefined";
                //            }

                return cellData;

            } catch (Exception e){

                return "undefined";

            }
        }

        private static InputStream readInputStreamFromFile() throws Exception {
            try {

                File f = new File("C:\\path to your file\\TestData.xlsx");

                InputStream is = new FileInputStream(f);
                try {
                    return new ByteArrayInputStream(IOUtils.toByteArray(is));
                } finally {
                    is.close();
                }
            } catch (IOException e) {
                throw new Exception(e);
            }
        }

    }
