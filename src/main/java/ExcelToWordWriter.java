import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads every row from a named sheet inside an Excel workbook (.xlsx),
 * writes each row (line by line) into a Word document (.docx), then writes
 * "Complete" into a status cell back on the Excel sheet to mark that the
 * transfer finished.
 *
 * Maven dependencies (add to pom.xml):
 *
 *   <dependency>
 *     <groupId>org.apache.poi</groupId>
 *     <artifactId>poi</artifactId>
 *     <version>5.2.5</version>
 *   </dependency>
 *   <dependency>
 *     <groupId>org.apache.poi</groupId>
 *     <artifactId>poi-ooxml</artifactId>
 *     <version>5.2.5</version>
 *   </dependency>
 */
public class ExcelToWordWriter {

    // ---- Fill these in for your use case ----
    private static final String EXCEL_FILE_PATH = "input.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String WORD_FILE_PATH = "output.docx";
    private static final int STATUS_COLUMN_INDEX = 0; // column A
    private static final int STATUS_ROW_INDEX = 0;    // row 1 (0-based)
    // ------------------------------------------

    public static void main(String[] args) throws IOException {
        List<List<String>> rows = readExcelRows(EXCEL_FILE_PATH, SHEET_NAME);
        System.out.println("Read " + rows.size() + " rows from sheet '" + SHEET_NAME + "'.");

        writeRowsToWord(rows, WORD_FILE_PATH);
        System.out.println("Finished writing rows to " + WORD_FILE_PATH);

        markStatusComplete(EXCEL_FILE_PATH, SHEET_NAME, STATUS_ROW_INDEX, STATUS_COLUMN_INDEX);
        System.out.println("Status cell updated to 'Complete' in " + EXCEL_FILE_PATH);
    }

    /** Reads every row of the given sheet name as a list of cell-value lists. */
    private static List<List<String>> readExcelRows(String excelFilePath, String sheetName) throws IOException {
        List<List<String>> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {
                List<String> cellValues = new ArrayList<>();
                for (Cell cell : row) {
                    // DataFormatter renders numbers/dates/formulas as their displayed text,
                    // so you get the same value a human would see in Excel.
                    cellValues.add(formatter.formatCellValue(cell));
                }
                rows.add(cellValues);
            }
        }

        return rows;
    }

    /** Appends each row as its own paragraph/line in a new Word document. */
    private static void writeRowsToWord(List<List<String>> rows, String wordFilePath) throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            for (List<String> row : rows) {
                String line = String.join(", ", row);

                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(line);
            }

            try (FileOutputStream fos = new FileOutputStream(wordFilePath)) {
                document.write(fos);
            }
        }
    }

    /** Writes the literal text "Complete" into the given cell and saves the workbook. */
    private static void markStatusComplete(String excelFilePath, String sheetName,
                                           int statusRowIndex, int statusColumnIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(statusRowIndex);
            if (row == null) {
                row = sheet.createRow(statusRowIndex);
            }

            Cell cell = row.getCell(statusColumnIndex);
            if (cell == null) {
                cell = row.createCell(statusColumnIndex);
            }
            cell.setCellValue("Complete");

            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }
        }
    }
}