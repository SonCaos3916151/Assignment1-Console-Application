import java.util.Objects;

public class StudentEnrollment {
    private Student student; //from Student Class
    private Course course; //from Course Class
    private String semester;

    public StudentEnrollment(Student student, Course course, String semester){
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollment that = (StudentEnrollment) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course) && Objects.equals(semester, that.semester);
    }

    public int hashCode() {
        return Objects.hash(student, course, semester);
    }

    @Override
    public String toString() {
        return "student: " + student.getS_id() + " " + student.getS_name() +
                " | course: " + course.getC_id() + " " + course.getC_name() +
                " | Semester: " + semester;
    }
    //Check duplicate enrollment with
}
