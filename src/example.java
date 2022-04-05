import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class example {

    public static void main(String[]args) throws FileNotFoundException {
        List<String>AllsID = new ArrayList<>();
        List<String>AllsCourse = new ArrayList<>();
        List<String>AllsSemester = new ArrayList<>();
        List<List<String>> records = new ArrayList<>();

        System.out.println("Type 'yes' if you want to enter your own CSV file path and 'no' if you want to use the default csv file path: ");
        Scanner pathChoiceScanner = new Scanner(System.in); //User choice to enter path or not
        String PathChoice = pathChoiceScanner.nextLine();

        Scanner pathScanner; //User enter path file
        String FilePath;
        //String line = "";
        if(PathChoice.equals("yes")){
            System.out.println("User input path will be use. Please enter the path: ");
            pathScanner = new Scanner(System.in);
            FilePath = pathScanner.nextLine();
        }else{
            FilePath = "src/default.csv";
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                records.add(Arrays.asList(values));

                //AllsCourse.add(values[3]);
                //AllsSemester.add(values[6]);
                //System.out.println(values[3]);
          }
        } catch (IOException e){
            e.printStackTrace();
        }
//
//        Scanner sc = new Scanner(new File("src/default.csv"));
//        //sc.useDelimiter("");   //sets the delimiter pattern
//        while (sc.hasNext())  //returns a boolean value
//        {
//            String next  = sc.next();
//            //System.out.print(next);  //find and returns the next complete token from this scanner
//            String[] values = line.split(",");
//            System.out.println(Arrays.toString(values));
////            AllsID.add(values[0]);
////            AllsCourse.add(values[3]);
////            AllsSemester.add(values[6]);
//        }
//        sc.close();  //closes the scanner


        System.out.println("Student id: " + records);
        //System.out.println("Student course: " + AllsCourse);
        //System.out.println("Student sem: " + AllsSemester);

    }
}
