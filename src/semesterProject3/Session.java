package semesterProject3;

import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Session {

    //Variables
    private Scanner response = new Scanner(System.in);
    private String topic, building, roomNumber, date, time;
    private  SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss z");//Select Date
    //Empty Constructor
    public Session(){}

    //Constructor To Create Event
    public Session(String topic, String building, String roomNumber, String date, String time){
        this.topic = topic;
        this.building = building;
        this.roomNumber = roomNumber;
        this.date = date;
        this.time = time;
        //System.out.println("Event Created: " + formatter.format(date) + "\n");
    }

    //Get Topic
    public String getTopic() {
        return topic;
    }

    //Set New Topic
    public void setTopic(String topic) {
        this.topic = topic;
    }

    //Get Building
    public String getBuilding() {
        return building;
    }

    //Set New Building
    public void setBuilding(String building) {
        this.building = building;
    }

    //Get Room Number
    public String getRoomNumber() {
        return roomNumber;
    }

    //Set New Room Number
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    //Get Date
    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    //Set New Date //You can use datetime.of() to set any date you want.
    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    //Change Event Topic
    public void changeTopic(){
        Boolean statement = false;
        while(!statement) {
            System.out.print("Are You Sure You Would Like To Change Session Topic??: ");
            String answer = response.nextLine();

            if (answer.equals("yes") || answer.equals("Yes")){
                System.out.print("Enter New Session Topic: ");
                String newTopic = response.nextLine();
                this.setTopic(newTopic);
                System.out.println("Successfully Changed Session Topic");
                statement = true;
            }
            else if(answer.equals("no") || answer.equals("No")){
                System.out.println("Session Topic Change (CANCELED)");
                break;
            }
            else {
                statement = false;
            }
        }
        System.out.println("");
    }

    //Change Building Location()
    public void changeBuilding(){
        Boolean statement = false;
        while(!statement) {
            System.out.print("Are You Sure You Would Like To Change Building Locations??: ");
            String answer = response.nextLine();

            if (answer.equals("yes") || answer.equals("Yes")) {
                System.out.print("Enter new Building Location: ");
                String newBuilding = response.nextLine();
                this.setBuilding(newBuilding);
                System.out.println("Successfully Changed Building Location");
                statement = true;
            }
            else if(answer.equals("no") || answer.equals("No")){
                System.out.println("Building Change (CANCELED)");
                break;
            }
            else{
                statement = false;
            }
        }
        System.out.println("");
    }

    //Change Room Number
    public void changeRoomNumber(){
        Boolean statement = false;
        while(!statement) {
            System.out.print("Are You Sure You Would Like To Room Number??: ");
            String answer = response.nextLine();

            if (answer.equals("yes") || answer.equals("Yes")) {
                System.out.print("Enter new Room Number ");
                String newRoomNumber = response.nextLine();
                this.setRoomNumber(newRoomNumber);
                System.out.println("Successfully Changed Room Number\n");
                statement = true;
            } else if(answer.equals("no") || answer.equals("No")){
                System.out.println("Room Number Change CANCELED\n");
                break;
            }else{
                statement = false;
            }
        }
        System.out.println("");
    }

    //Sets Date For Session
    public void customDate(){
        System.out.println("Enter Scheduled Day:(Example: 25-Mar-2020) ");
        String date = response.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date2 = null;
        try {
            //Parsing the String
            date2 = dateFormat.parse(date);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(date2);
    }

    //Deletes Current Event
    public void deleteEvent(){
        this.topic = null;
        this.building = null;
        this.roomNumber = null;
    }

    @Override //ToString Prints Event Info
    public String toString() {
        return "New Session Created: " +
                "\nTopic: '" + topic +
                "\nBuilding: '" + building +
                "\nRoomNumber: '" + roomNumber +
                "\nDate: " + date;
             //   "\nDate: " + DatePicker + '\' +
                /*", formatter=" + formatter +
                '}';*/
    }
}//End of Class
