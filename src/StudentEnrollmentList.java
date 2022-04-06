import java.util.ArrayList;
import java.util.List;

public class StudentEnrollmentList implements StudentEnrollmentManager{
    private static final List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();
    CSVcheck csVcheck = new CSVcheck();

    @Override
    public void add(String studentID, String semester, String courseID,String FilePath){
        String[]relatedEnrollmentInfo = csVcheck.getRelatedInfo(studentID,semester,courseID,FilePath);
        Student newStudent = new Student();
        newStudent.setS_id(studentID);
        newStudent.setS_name(relatedEnrollmentInfo[0]);
        newStudent.setBirthdate(relatedEnrollmentInfo[1]);
        Course newStudentCourse = new Course();
        newStudentCourse.setC_id(courseID);
        newStudentCourse.setC_name(relatedEnrollmentInfo[2]);
        newStudentCourse.setNoOfCredits(relatedEnrollmentInfo[3]);
        StudentEnrollment newEnrollment = new StudentEnrollment(newStudent,newStudentCourse,semester);
        studentEnrollmentList.add(newEnrollment);
        System.out.println("Enrollment has been created\n" + newEnrollment);
    }
    @Override
    public void update(String studentID, String semester, String courseID,String courseIDToChange,String courseSemesterToChange, String FilePath) {
        delete(studentID,courseID,semester);
        add(studentID,courseSemesterToChange,courseIDToChange,FilePath);
        System.out.println("Your enrollment has been updated");
    }

    @Override
    public void delete(String studentID,String courseID,String semester) {
        int index = 0;
        for (StudentEnrollment student : studentEnrollmentList) {
            if (student.getStudent().getS_id().equals(studentID)) { //if student ID equal
                if (student.getCourse().getC_id().equals(courseID)) { //if student course ID equal
                    if (student.getSemester().equals(semester)) { //if semester equal
                        index = studentEnrollmentList.indexOf(student);
                    }
                }
            }
        }
        studentEnrollmentList.remove(index);
        System.out.println("Enrollment Info has been deleted\n");
    }

    public int getOne(String studentID) { //get one student, if multiple enrollment, get all of that enrollment
        int count = 0;
        for(StudentEnrollment student : studentEnrollmentList) {
            if (student.getStudent().getS_id().equals(studentID)) { //if student ID equal
                System.out.println(student); //print out student
                count = count + 1;
            }
        }
        return count;
    }
    @Override
    public List<StudentEnrollment> getAll() {
        return studentEnrollmentList;
    }

    boolean checkDuplicateEnrollment(String studentID, String studentCourseID, String semester) {
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (enrollment.getStudent().getS_id().equals(studentID)) { //if student ID equal
                if (enrollment.getCourse().getC_id().equals(studentCourseID)) { //if student course ID equal
                    if (enrollment.getSemester().equals(semester)) { //if semester equal
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

