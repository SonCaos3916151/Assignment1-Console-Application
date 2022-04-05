import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) throws Exception {

        System.out.println("Type 'yes' if you want to enter your own CSV file path and 'no' if you want to use the default csv file path: ");
        Scanner pathChoiceScanner = new Scanner(System.in); //User choice to enter path or not
        String PathChoice = pathChoiceScanner.nextLine();

        Scanner pathScanner; //User enter path file
        String FilePath;
        String line = "";
        if(PathChoice.equals("yes")){
            System.out.println("User input path will be use. Please enter the path: ");
            pathScanner = new Scanner(System.in);
            FilePath = pathScanner.nextLine();
        }else{
            FilePath = "src/default.csv";
        }
        CSVcheck csVcheck = new CSVcheck();
        //csVcheck.convertCSVtoList(FilePath); //convert csv file into list
        StudentEnrollmentList enrollmentList = new StudentEnrollmentList();

        System.out.println("Welcome to the Student Enrollment Console Application!");
        boolean exit = false;
        while(!exit){
            System.out.println("""
                    Input '1' to Enroll a student for 1 semester
                    Input '2' to Update student enrollment
                    Input '3' to Print all of your Enrolled course in 1 semester
                    Input '4' to Show all students in course in 1 semester
                    Input '5' to Show all courses in 1 semester
                    Input '6' to Quit""");

            System.out.println("Please input your option: ");
            Scanner scanner1= new Scanner(System.in);
            String option = "";
            if(scanner1.hasNext()){
                option = scanner1.next();
            }
            if ("1".equals(option)) { //enroll a student enroll with id, semester and course and then put the other data from csv file
                System.out.println("Create new enrollment");
                Scanner option1Scanner = new Scanner(System.in);

                System.out.println("Please enter your student ID: ");
                String inputStudentID = option1Scanner.nextLine();
                System.out.println("Please enter your semester: ");
                String inputSemester = option1Scanner.nextLine();
                System.out.println("Please enter your course ID: ");
                String inputCourseID = option1Scanner.nextLine();

                boolean existInCSV = csVcheck.checkWithCSV(inputStudentID,inputSemester,inputCourseID,FilePath);
                boolean existInEnrollmentList = enrollmentList.checkDuplicateEnrollment(inputStudentID,inputSemester,inputCourseID);

                if(existInCSV && !existInEnrollmentList){
                    String[]relatedEnrollmentInfo = csVcheck.getRelatedInfo(inputStudentID,inputSemester,inputCourseID,FilePath);
                    Student newStudent = new Student();
                    newStudent.setS_id(inputStudentID);
                    newStudent.setS_name(relatedEnrollmentInfo[0]);
                    newStudent.setBirthdate(relatedEnrollmentInfo[1]);
                    Course newStudentCourse = new Course();
                    newStudentCourse.setC_id(inputCourseID);
                    newStudentCourse.setC_name(relatedEnrollmentInfo[2]);
                    newStudentCourse.setNoOfCredits(Integer.parseInt(relatedEnrollmentInfo[3]));
                    StudentEnrollment newEnrollment = new StudentEnrollment(newStudent,newStudentCourse,inputSemester);
                    enrollmentList.add(newEnrollment); //add new enrollment into enrollment list
                }else if(!existInCSV){
                    System.out.println("Your inputted information doesn't exists in the CSV file, Please retry");
                }else {
                    System.out.println("Your inputted information already exists in the enrollment list, Please retry");
                }
            }
            else if("2".equals(option)){
                Scanner option2Scanner = new Scanner(System.in);
                System.out.println("Please enter your student ID: ");
                String inputStudentID = option2Scanner.nextLine();
                boolean existInEnrollmentList = enrollmentList.checkDuplicateEnrollment(inputStudentID,"not needed","not needed");
                if (!existInEnrollmentList){
                    System.out.println("Your student ID doesn't exist in the enrollment list");
                    continue;
                }
                //print all enrollment of a student with that student id

                boolean Continue = true;
                while(Continue) {
                    System.out.println("Input '1' if you want to delete a course and '2' if you want to add a new course");
                    Scanner updateOption = new Scanner(System.in);
                    System.out.println("Please enter your option: ");
                    String updateOptionString = updateOption.nextLine();
                    if (updateOptionString.equals("1")) { //delete course or courses depends on user
                        System.out.println("Please enter the course you want to delete: ");
                        Scanner deleteCourseID = new Scanner(System.in);
                        String courseToDelete = deleteCourseID.nextLine();
                        System.out.println("Please enter the semester of that course you want to delete: ");
                        Scanner deleteCourseSemester = new Scanner(System.in);
                        String courseSemester = deleteCourseSemester.nextLine();

                        boolean existInCSV = csVcheck.checkWithCSV(inputStudentID,courseSemester,courseToDelete,FilePath);
                        if(!existInCSV){
                            System.out.println("Your input Course ID and semester doesn't exist, please try again");
                            continue;
                        }



                        System.out.println("You have successfully delete your course");
                    } else { //add new course
                        System.out.println("Please enter the course ID you want to add: ");
                        Scanner addCourse = new Scanner(System.in);
                        String addCourseID = addCourse.nextLine();
                        System.out.println("Please enter the semester of the course you want to add: ");
                        Scanner addCourseSemester = new Scanner(System.in);
                        String courseSemester = addCourseSemester.nextLine();
                        boolean existInCSV = csVcheck.checkWithCSV(inputStudentID,courseSemester,addCourseID,FilePath);
                        if(!existInCSV){
                            System.out.println("Your input Course ID and/or semester doesn't exist, please try again");
                            continue;
                        }
                        String[]relatedEnrollmentInfo = csVcheck.getRelatedInfo(inputStudentID,courseSemester,addCourseID,FilePath);
                        Student addEnrollment = new Student();
                        addEnrollment.setS_id(inputStudentID);
                        addEnrollment.setS_name(relatedEnrollmentInfo[0]);
                        addEnrollment.setBirthdate(relatedEnrollmentInfo[1]);
                        Course addEnrollmentCourse = new Course();
                        addEnrollmentCourse.setC_id(addCourseID);
                        addEnrollmentCourse.setC_name(relatedEnrollmentInfo[2]);
                        addEnrollmentCourse.setNoOfCredits(Integer.parseInt(relatedEnrollmentInfo[3]));
                        StudentEnrollment newEnrollment = new StudentEnrollment(addEnrollment,addEnrollmentCourse,courseSemester);
                        enrollmentList.add(newEnrollment); //add new enrollment into enrollment list
                        System.out.println("You have successfully add your course");
                    }
                    Scanner continueOptionScanner = new Scanner(System.in);
                    System.out.println("Type 'no' to stop updating enrollment and type anything else to continue updating enrollment: ");
                    String continueOption2 = continueOptionScanner.nextLine();
                    if(continueOption2.equals("no")){
                        Continue = false;
                    }
                }
            }
            else if("3".equals(option)){ //call printCoursesForStudent

                System.out.println("Please enter the student ID: ");
                System.out.println("Please enter the semester: ");

            }
            else if("4".equals(option)){ //call printStudentInCourse
                System.out.println("Please enter the course ID: ");
                System.out.println("Please enter the semester: ");


            }
            else if("5".equals(option)){ //call printAllCourses
                System.out.println("Please enter the semester: ");
                Scanner option5Scanner = new Scanner(System.in);
                String option5 = option5Scanner.nextLine();
                printFunction printAll_C = new printFunction();
                printAll_C.printAllCourses(option5);
            }
            else if("6".equals(option)){
                System.out.println("Thank you for using the application");
                exit = true;
            }
            else{
                System.out.println("Please enter a valid input");
                break;
            }
        }
    }
}
