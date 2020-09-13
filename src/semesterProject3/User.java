package semesterProject3;

import java.util.Random;
import java.util.Scanner;

public class User {
    private String firstName, lastName, email, major;
    private int ID = 0;
    private static int numOfStudent = 0;
    Scanner scanner = new Scanner(System.in);
    Random randomInt = new Random();//Creates Student ID
    int studentID = randomInt.nextInt();

    //Empty Constructor
    public User(){}

    //Constructor Type Arguments
    public User(String firstName, String lastName, String email, String major){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.major = major;
            ID++;
            numOfStudent++;
    }

    //Get First Name
    public String getFirstName() {
        return firstName;
    }

    //Set First Name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Get Last Name
    public String getLastName() {
        return lastName;
    }

    //Set Last Name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Get Email
    public String getEmail() {
        return email;
    }

    //Set Email
    public void setEmail(String email) {
        this.email = email;
    }

    //Get Major
    public String getMajor() {
        return major;
    }

    //Set Major
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", ID=" + ID +
                '}';
    }
}//end of class
