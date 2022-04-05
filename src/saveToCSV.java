import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class saveToCSV {

    StudentEnrollmentList enrollmentList = new StudentEnrollmentList();
    void saveToCSV() {
        try { //delete file if exist
            Files.deleteIfExists(Paths.get("src/enrollment.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter csvOutputFile = new PrintWriter("src/enrollment.csv");
            StringBuilder sb = new StringBuilder();
            //save to CSV part
            for (StudentEnrollment enrollmentList : enrollmentList.getAll()) {
                sb.append(enrollmentList.getStudent().getS_id());
                sb.append(",");
                sb.append(enrollmentList.getStudent().getS_name());
                sb.append(",");
                sb.append(enrollmentList.getStudent().getBirthdate());
                sb.append(",");
                sb.append(enrollmentList.getCourse().getC_id());
                sb.append(",");
                sb.append(enrollmentList.getCourse().getC_name());
                sb.append(",");
                sb.append(enrollmentList.getCourse().getNoOfCredits());
                sb.append("\n");
            }
            csvOutputFile.write(sb.toString());
            csvOutputFile.close();
            System.out.println("enrollment.csv file has been created/updated");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
