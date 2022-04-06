import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args){

        System.out.println("Type 'yes' if you want to enter your own CSV file path and type anything else if you want to use the default csv file path: ");
        Scanner pathChoiceScanner = new Scanner(System.in); //User choice to enter path or not
        String PathChoice = pathChoiceScanner.nextLine();

        Scanner pathScanner; //User enter path file
        String FilePath;
        if(PathChoice.equals("yes")){
            System.out.println("User input path will be use. Please enter the path: ");
            pathScanner = new Scanner(System.in);
            FilePath = pathScanner.nextLine();
        }else{
            FilePath = "src/default.csv";
        }
        CSVcheck csVcheck = new CSVcheck();
        StudentEnrollmentList enrollmentList = new StudentEnrollmentList();

        System.out.println("Welcome to the Student Enrollment Console Application!");
        boolean exit = false;
        while(!exit){
            printFunction printFunction = new printFunction(); //for option 3,4,5
            System.out.println("""
                    Input '1' to Enroll a student for 1 semester
                    Input '2' to Update student enrollment
                    Input '3' to Show all of your Enrolled course in 1 semester
                    Input '4' to Show all students in course in 1 semester
                    Input '5' to Show all courses in 1 semester
                    Input '6' to Save all enrollment information to a CSV file
                    Input '7' to Quit""");

            System.out.println("Please input your option: ");
            Scanner scanner1= new Scanner(System.in);
            String option = "";
            if(scanner1.hasNext()){
                option = scanner1.next();
            }
            if ("1".equals(option)) { //enroll a student enroll with id, semester and course and then put the other data from csv file
                Scanner option1Scanner = new Scanner(System.in);
                System.out.println("Please enter your student ID: ");
                String inputStudentID = option1Scanner.nextLine();
                System.out.println("Please enter your semester: ");
                String inputSemester = option1Scanner.nextLine();
                System.out.println("Please enter your course ID: ");
                String inputCourseID = option1Scanner.nextLine();

                boolean existInCSV = csVcheck.checkWithCSV(inputStudentID,inputSemester,inputCourseID,FilePath);
                boolean existInEnrollmentList = enrollmentList.checkDuplicateEnrollment(inputStudentID,inputCourseID,inputSemester);

                if(existInCSV && !existInEnrollmentList){ //add new course
                    enrollmentList.add(inputStudentID,inputSemester,inputCourseID,FilePath);
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
                int count = enrollmentList.getOne(inputStudentID);
                if(count == 0){
                    System.out.println("Your student ID doesn't exist in the enrollment list");
                    continue;
                }
                boolean Continue = true;
                while(Continue) {
                    System.out.println("Please enter the course ID you want to delete/add or change: ");
                    Scanner sc1 = new Scanner(System.in);
                    String courseToUpdate = sc1.nextLine();
                    System.out.println("Please enter the semester of that course you want to delete/add or change: ");
                    Scanner sc2 = new Scanner(System.in);
                    String courseSemester = sc2.nextLine();
                    boolean existInCSV = csVcheck.checkWithCSV(inputStudentID, courseSemester, courseToUpdate, FilePath);
                    if (!existInCSV) {
                        System.out.println("Your input Course ID and/or semester doesn't exist in default file, please try again");
                        continue;
                    }

                    boolean validInput = false;
                    while(!validInput) {
                        System.out.println("Input '1' if you want to delete the inputted course ID,'2' if you want to add a the new course ID and '3' if you want to change(update) an enrollment with the inputted course ID and semester");
                        Scanner updateOption = new Scanner(System.in);
                        System.out.println("Please enter your option: ");
                        String updateOptionString = updateOption.nextLine();
                        switch (updateOptionString) {
                            case "1" -> { //delete course
                                boolean existInEnrollmentList = enrollmentList.checkDuplicateEnrollment(inputStudentID, courseToUpdate, courseSemester);
                                if (!existInEnrollmentList) {
                                    System.out.println("Your course ID or semester that course is in doesn't exist in the enrollment list");
                                    continue;
                                }
                                enrollmentList.delete(inputStudentID, courseToUpdate, courseSemester);
                                validInput = true;
                            }
                            case "2" -> { //add new course
                                boolean existInEnrollmentList = enrollmentList.checkDuplicateEnrollment(inputStudentID, courseToUpdate,courseSemester);
                                if (existInEnrollmentList) {
                                    System.out.println("An enrollment with similar student ID, course ID and semester have already exists, please try again");
                                    continue;
                                }
                                enrollmentList.add(inputStudentID, courseSemester, courseToUpdate, FilePath);
                                System.out.println("You have successfully add your course");
                                validInput = true;
                            }
                            case "3" -> {  //change an enrollment content, can only change the course content
                                System.out.println("Your previous entered course information will be change with your entered information");
                                System.out.println("Please enter the course ID you want to change to: ");
                                Scanner sc1Change = new Scanner(System.in);
                                String courseIDToChange = sc1Change.nextLine();
                                System.out.println("Please enter the semester of the course you want to change to: ");
                                Scanner sc2Change = new Scanner(System.in);
                                String courseSemesterToChange = sc2Change.nextLine();
                                boolean ChangeExistInCSV = csVcheck.checkWithCSV(inputStudentID, courseSemesterToChange, courseIDToChange, FilePath);
                                if (!ChangeExistInCSV) {
                                    System.out.println("Your input Course ID to change and/or semester to change doesn't exist in default file");
                                    continue;
                                }
                                enrollmentList.update(inputStudentID, courseToUpdate, courseSemester, courseIDToChange, courseSemesterToChange, FilePath);
                                validInput = true;
                            }
                            default -> System.out.println("Please enter '1','2', or '3'");
                        }
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
                Scanner option3S_ID = new Scanner(System.in);
                System.out.println("Please enter the student ID: ");
                String studentID = option3S_ID.nextLine();
                Scanner option3Semester = new Scanner(System.in);
                System.out.println("Please enter the semester: ");
                String semester = option3Semester.nextLine();
                printFunction.printCoursesForStudent(studentID,semester);
            }
            else if("4".equals(option)){ //call printStudentInCourse
                Scanner option4C_ID = new Scanner(System.in);
                System.out.println("Please enter the course ID: ");
                String courseID = option4C_ID.nextLine();
                Scanner option4Semester = new Scanner(System.in);
                System.out.println("Please enter the semester: ");
                String semester = option4Semester.nextLine();

                printFunction.printStudentsInCourse(courseID,semester);

            }
            else if("5".equals(option)){ //call printAllCourses
                System.out.println("Please enter the semester: ");
                Scanner option5Scanner = new Scanner(System.in);
                String option5Semester = option5Scanner.nextLine();
                printFunction printAll_C = new printFunction();
                printAll_C.printAllCourses(option5Semester,FilePath);

            }
            else if("6".equals(option)){
                System.out.println("All of the enrollment so far will be save in a csv file");
                saveToCSV saveEnrollment = new saveToCSV();
                saveEnrollment.saveToCSV();
            }
            else if("7".equals(option)){
                System.out.println("Thank you for using the application");
                exit = true;
            }
            else{
                System.out.println("Please enter a valid input");
            }
        }
    }
}
