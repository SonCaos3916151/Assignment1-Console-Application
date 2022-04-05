import java.util.ArrayList;
import java.util.List;

public class StudentEnrollmentList implements StudentEnrollmentManager{
    private List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();


    @Override
    public void add(StudentEnrollment newEnrollment) {
        studentEnrollmentList.add(newEnrollment);
        System.out.println("New enrollment has been created\n" + newEnrollment.toString());
    }

    //change update to update to csv file
    @Override
    public void update(StudentEnrollment toUpdate, StudentEnrollment updateInfo) {
        toUpdate.setStudent(updateInfo.getStudent());
        toUpdate.setCourse(updateInfo.getCourse());
        toUpdate.setSemester(updateInfo.getSemester());
        System.out.println("Enrollment has been updated :\n" + toUpdate.toString());
    }

    @Override
    public void delete(StudentEnrollment enrollment) {
        studentEnrollmentList.remove(enrollment);
        System.out.println("Enrollment Info has been deleted\n");
    }

    @Override
    public void getOne(String studentID) { //get one student, if he have multiple enrollment, get all of that enrollment

    }
    @Override
    public List<StudentEnrollment> getAll() {
        return this.studentEnrollmentList;
    }

    boolean checkDuplicateEnrollment(String studentID, String studentCourseID, String semester) {
        if(studentCourseID.equals("not needed") && semester.equals("not needed") ) {
            for (StudentEnrollment student : studentEnrollmentList) {
                if (student.getStudent().getS_id().equals(studentID)) { //if student ID equal
                    return true;
                }
            }
        }else {
            for (StudentEnrollment student : studentEnrollmentList) {
                if (student.getStudent().getS_id().equals(studentID)) { //if student ID equal
                    if (student.getCourse().getC_id().equals(studentCourseID)) { //if student course ID equal
                        if (student.getSemester().equals(semester)) { //if semester equal
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

