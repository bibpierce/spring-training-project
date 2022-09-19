package com.bibvip.springtest.services.impl;

import com.bibvip.springtest.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class WriteData {

    public void exportAll(List<User> userList) throws IOException {
        writeData(userList);
    }

    public void singleExport(User user) throws IOException {
        writeData(Collections.singletonList(user));
    }


    public void writeData(List<User> userList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" User Data ");

        XSSFRow row;

        writeHeaderLine(spreadsheet);

        Map<String, Object[]> userData = new TreeMap<>();
        for (int i = 0; i < userList.size(); i++) {

            long id = userList.get(i).getId();
            String name = userList.get(i).getName();
            byte[] picture = userList.get(i).getPicture();
            byte[] vacCard = userList.get(i).getVacCard();
            byte[] primaryId = userList.get(i).getPrimaryId();
            byte[] secondaryId = userList.get(i).getSecondaryId();

            userData.put(String.valueOf(i), new Object[]{id, name,
                    picture, vacCard, primaryId, secondaryId});
        }

        Set<String> keyid = userData.keySet();

        int rowid = 1;

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = userData.get(key);
            int cellid = 0;


            for (Object obj : objectArr) {

                if (cellid <= 1) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue(String.valueOf(obj));

                } else {
                    cellid++;

                    int all = workbook.addPicture((byte[]) obj, Workbook.PICTURE_TYPE_PNG);
                    XSSFDrawing drawing = spreadsheet.createDrawingPatriarch();
                    XSSFClientAnchor allAnchor = new XSSFClientAnchor();

                    allAnchor.setCol1(cellid - 1);
                    allAnchor.setCol2(cellid);
                    allAnchor.setRow1(rowid - 1);
                    allAnchor.setRow2(rowid);

                    drawing.createPicture(allAnchor, all);

                }
            }

            for (int i = 0; i < 7; i++) {
                Cell cell = row.createCell(cellid++);
                spreadsheet.setColumnWidth(i, 4200);
                cell.getRow().setHeight((short) 1200);
            }

            FileOutputStream out = new FileOutputStream(
                    new File("Excel-Export.xlsx"));
            workbook.write(out);
            out.close();
        }
    }



    public void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Name");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("2x2 Picture");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Vaccination Card");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Primary ID");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Secondary ID");
    }

}
