import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVcheck { //input all needed elements from csv file into arraylist to check and get.
    String line = "";
    boolean checkWithCSV(String tempStudentID, String tempSemester, String tempCourseID,String FilePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[0].equals(tempStudentID)) {
                    if (values[3].equals(tempCourseID)) {
                        if(values[6].equals(tempSemester)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    String[] getRelatedInfo(String tempStudentID, String tempSemester, String tempCourseID,String FilePath){
        String[]relatedEnrollmentInfo = new String[4];
        try{
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[0].equals(tempStudentID)) {
                    if (values[3].equals(tempCourseID)) {
                        if(values[6].equals(tempSemester)) {
                            relatedEnrollmentInfo[0] = values[1]; //s_name
                            relatedEnrollmentInfo[1] = values[2]; //s_DOB
                            relatedEnrollmentInfo[2] = values[4]; //s_CourseName
                            relatedEnrollmentInfo[3] = values[5]; //Course credits
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return relatedEnrollmentInfo;
    }
}
