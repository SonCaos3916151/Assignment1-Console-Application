import java.util.List;

public interface StudentEnrollmentManager {

    public void add(StudentEnrollment newEnrollment);
    public void update(StudentEnrollment beforeUpdate, StudentEnrollment updateData);
    public void delete(StudentEnrollment enrollment);
    public void getOne(String studentID);
    public List<StudentEnrollment> getAll();
}
