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

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "student: " + student.getS_id() + " Student Name: " + student.getS_name() + "(" + student.getBirthdate()+ ")"+
                " | course: " + course.getC_id() + " Course name: " + course.getC_name() + " Course total credits: " + course.getNoOfCredits()+
                " | Semester: " + semester;
    }
}
