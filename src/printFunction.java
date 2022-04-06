import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class printFunction{
    StudentEnrollmentList enrollmentList = new StudentEnrollmentList();
    int count = 0;
    void printAllCourses(String semester,String FilePath){ // print all course for 1 semester. get from default.csv
        String line;
        List<String> courseInSemester = new ArrayList<>();
        int count = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[6].equals(semester)){
                    if(!courseInSemester.contains(values[3])){
                        System.out.println("Course id: "+values[3]+" | Course name: "+values[4]+" | Number of Credits: "+values[5]);
                        count = count + 1;
                        courseInSemester.add(values[3]);
                    }
                }
            }
            System.out.println("Found "+count+" course/courses in semester "+semester);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    void printCoursesForStudent(String sID,String semester){ //print all course for 1 student in 1 semester
        for(StudentEnrollment enrollment : enrollmentList.getAll()) {
            if(enrollment.getStudent().getS_id().equals(sID)){
                if (enrollment.getSemester().equals(semester)) { //if semester equal
                    System.out.println(enrollment.getCourse().toString());
                    count = count + 1;
                }
            }
        }
        if(count == 0){
            System.out.println("Found 0 courses for this student in this semester");
        }
    }
    void printStudentsInCourse(String course,String semester){ //print all student in 1 course
        for(StudentEnrollment enrollment : enrollmentList.getAll()) {
            if (enrollment.getSemester().equals(semester)) { //if semester equal
                if(enrollment.getCourse().getC_id().equals(course)){
                    System.out.println(enrollment.getStudent().toString());
                    count = count + 1;
                }
            }
        }
        if(count == 0){
            System.out.println("Found 0 student in this course in this semester");
        }
    }

}
