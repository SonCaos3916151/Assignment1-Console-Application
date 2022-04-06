public class Student {
    private String s_id;
    private String s_name;
    private String birthdate;

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Student ID = " + s_id + " | " +
                " Student Name = " + s_name + " | " +
                " Student Date Of Birth = " + birthdate;

    }
}
