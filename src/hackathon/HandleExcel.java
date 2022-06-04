package hackathon;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class HandleExcel {
    
    public static String calculateGrade(int avg){
        if(avg>=190)
            return "S";
        else if(avg>=170)
           return "A";
        else if(avg>=150)
            return "B";
        else if(avg>=130)
            return "C";
        else if(avg>=110)
            return "D";
        else if(avg>=100)
            return "E";
        else
            return "F";
    }
    
    public static boolean processFile(String filePath){
        try {  
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file); 
            Database db = new Database();

            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
            itr.next();
            
            while (itr.hasNext()){  
                Row row = itr.next();  
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column 
                String regNo = cellIterator.next().getStringCellValue();
                String courseCode = cellIterator.next().getStringCellValue();
                int cat1 = (int) cellIterator.next().getNumericCellValue();
                int cat2 = (int) cellIterator.next().getNumericCellValue();
                int da1 = (int) cellIterator.next().getNumericCellValue();
                int da2 = (int) cellIterator.next().getNumericCellValue();
                int fat = (int) cellIterator.next().getNumericCellValue();
                               
                System.out.println(regNo + " " + courseCode + " " + cat1 + " " + cat2 + " " + da1 + " " + da2);
                int total = cat1 + cat2 + da1 + da2 + fat;
                String grade = calculateGrade(total);
                String q = "insert into marks values(DEFAULT,'" + regNo + "','" + courseCode + "'," + cat1 + "," + cat2 + "," + da1 + "," + da2 + "," + fat + ",'" + grade + "');";
                System.out.println(q);
                db.executeQuery(q);
            }  
            return true;
        } 
        catch(Exception e) {  
            e.printStackTrace();
            return false;
        }
    }
}
