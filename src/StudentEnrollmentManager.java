import java.util.List;

public interface StudentEnrollmentManager {

    void add(String studentID, String semester, String courseID,String FilePath);
    void update(String studentID, String semester, String courseID,String courseIDToChange,String courseSemesterToChange, String FilePath);
    void delete(String studentID,String semester,String courseID);
    int getOne(String studentID);
    List<StudentEnrollment> getAll();
}
