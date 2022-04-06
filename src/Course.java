public class Course {
    private String c_id;
    private String c_name;
    private String noOfCredits;

    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public String getNoOfCredits() {
        return noOfCredits;
    }
    public void setNoOfCredits(String noOfCredits) {
        this.noOfCredits = noOfCredits;
    }
    @Override
    public String toString() {
        return "Course ID = " + c_id + " | " +
                " Course Name = " + c_name + " | " +
                " noOfCredits = " + noOfCredits;
    }
}
