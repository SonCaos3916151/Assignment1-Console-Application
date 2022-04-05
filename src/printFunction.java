public class printFunction{
    StudentEnrollmentList enrollmentList = new StudentEnrollmentList();
    int count = 0;
    void printAllCourses(String semester){ // printt all course for 1 semester
        for(StudentEnrollment courses : enrollmentList.getAll()) {
            if (courses.getSemester().equals(semester)) {
                System.out.println(courses);
                count = count + 1;
            }
        }
        if(count == 0){
            System.out.println("Found 0 course in this semester");
        }
    }
    void printCoursesForStudent(String sID,String semester){ //print all course for 1 student in 1 semester
        for(StudentEnrollment Scourses : enrollmentList.getAll()) {
            if (Scourses.getSemester().equals(semester)) { //if semester equal
                if(Scourses.getStudent().getS_id().equals(sID)){
                    System.out.println(Scourses);
                    count = count + 1;
                }
            }
        }
        if(count == 0){
            System.out.println("Found 0 courses for this student in this semester");
        }
    }
    void printStudentsInCourse(String course,String semester){ //print all student in 1 course
        for(StudentEnrollment student : enrollmentList.getAll()) {
            if (student.getSemester().equals(semester)) { //if semester equal
                if(student.getCourse().getC_id().equals(course)){
                    System.out.println(student);
                    count = count + 1;
                }
            }
        }
        if(count == 0){
            System.out.println("Found 0 student in this course in this semester");
        }
    }

}
