// library imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Predictor {
    // static members
    private static String wrPath, dayPath, hourPath;
    private static String[] timeList, dayList;
    private static int lowest, min, lowestWr, recCount;
    private static int[] wrList;
    private static FileOutputStream wr = null, day = null, hour = null;
    public static void main(String[] args) throws IOException {
        // getting current working directory
        String cwd = System.getProperty("user.dir");
        // finding data file
        String filePath = cwd + "/sampleData.csv";
        // initializing scanner obj to read data file
        Scanner reader = new Scanner(new File(filePath));
        // declaring data separator for csv
        reader.useDelimiter(",");
        // initializing obj pointers
        try{
            wr = new FileOutputStream(cwd+"/wrData");
            day = new FileOutputStream(cwd+"/dayData");
            hour = new FileOutputStream(cwd+"/hourData");
        }
        catch (Exception e){
            System.out.println("Exception caught\n"+e.getMessage());
        }
        // counting records for initialization of arrays later
        recCount = 0;
        // reading data file
        while(reader.hasNext()){
            // storing data
            String data = reader.next().replaceAll("\"", "");
            String date;
            if(reader.hasNext())
                date = reader.next().replaceAll("\"", "");
            else
                continue;
            // parsing date via regular expressions
            String time = date.replaceAll(".* (\\d{2}:\\d{2}:\\d{2}) .*", "$1");
            String dayOfTheWeek = date.substring(0, 3);
            int dataWR = Integer.parseInt(data.substring(data.indexOf("WR")+2, data.indexOf("CM")).replaceAll("[^0-9]", ""));
            // writing data to separate text files
            if(!(wr==null || day == null || hour == null)){
                wr.write((dataWR+"\n").getBytes());
                day.write((dayOfTheWeek+"\n").getBytes());
                hour.write((time+"\n").getBytes());
            }
            // counting each iteration of the loop to build arrays later
            recCount++;
        }
        // storing path to text files that hold previously read data
        wrPath = cwd + "/wrData";
        hourPath = cwd + "/hourData";
        dayPath = cwd + "/dayData";
        int input = 69, i = 69;
        do {
            // input reader
            Scanner consoleReader = new Scanner(System.in);
            // menu header
            System.out.println("***************************\nWelcome to GymCrowdPredict\n***************************");
            // menu contents
            System.out.println("1) The absolute best time to attend the gym");
            System.out.println("2) The best time to attend the gym with a given minimum attendance");
            System.out.println("3) The best time to attend the gym on a certain day");
            System.out.print("Input: ");
            // input variable
            input = consoleReader.nextInt();
            // switch case for different inputs
            switch(input){
                // "absolute best" case
                case(1):
                    i = dataReader();
                    System.out.println("The absolute best time to attend the gym is on a " + getDayLong(dayList[i]) + " at " + getTime(timeList[i]) + " with " + wrList[i] + " other gym goers.");
                    break;
                // "given minimum" case
                case(2):
                    // taking and storing user input
                    int newMin = 0;
                    System.out.print("Please provide a minimum attendance: ");
                    // valid input check
                    try {
                        newMin = consoleReader.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Invalid min attendance arg\nTry again");
                        continue;
                    }
                    // fetching proper array index and passing new min value
                    i = dataReader(newMin);
                    // invalid case
                    if (i == -1){
                        System.out.println("No time found with a minimum of "+ newMin + " other gym goers.");
                        break;
                    }
                    System.out.println("The best time to attend the gym with a minimum of " + newMin + " others is on a " + getDayLong(dayList[i]) + " at " + getTime(timeList[i])+".");
                    break;
                // "given day" case
                case(3):
                    // input prompt and storage
                    System.out.print("Please provide a day of the week: ");
                    String givenDay = consoleReader.next().replaceAll(" ","");
                    // fetching proper array index and passing day param
                    i = dataReader(givenDay);
                    // valid input check
                    if ((getDayLong(givenDay).compareToIgnoreCase("Invalid arg")==0)&&(getDayShort(givenDay).compareToIgnoreCase("Invalid arg")==0)){
                        System.out.println("Invalid day arg");
                        break;
                    }
                    // fetching full day name
                    if (givenDay.length()<=3)
                        givenDay = getDayLong(givenDay);
                    System.out.println("The best time to attend the gym on a "+givenDay+" with minimum other gym goers is at "+ getTime(timeList[i]) + " with " + wrList[i] + " others.");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            // exit prompt
            System.out.print("Enter 0 quit or any other token to repeat: ");
            try{
                input = Integer.parseInt(consoleReader.next());
            }
            catch (NumberFormatException e){
                // empty catch clause so any tokens will be accepted
            }
        } while (input != 0);
        System.out.println("Goodbye :)");
    }

    // method for finding absolute best time
    private static int dataReader() throws FileNotFoundException {
        // building scanner objects to read text files
        Scanner wrReader = new Scanner(new File(wrPath));
        Scanner hourReader = new Scanner(new File(hourPath));
        Scanner dayReader = new Scanner(new File(dayPath));
        // initializing arrays
        wrList = new int[recCount];
        timeList = new String[recCount];
        dayList = new String[recCount];
        // arbitrary high value as initial value for comparison to find the lowest value
        lowestWr = 100000;
        lowest = -1;
        min = 0;
        for(int i = 0; i < recCount; i++) {
            int wrRec = Integer.parseInt(wrReader.next());
            wrList[i] =  wrRec;
            timeList[i] = hourReader.next();
            dayList[i] = dayReader.next();
            if (wrRec < lowestWr && wrRec > min) {
                lowestWr = wrRec;
                lowest = i;
            }
        }
        return lowest;
    }

    // method for finding best time with a minimum attendance
    private static int dataReader(int newMin) throws FileNotFoundException {
        // building scanner objects to read text files
        Scanner wrReader = new Scanner(new File(wrPath));
        Scanner hourReader = new Scanner(new File(hourPath));
        Scanner dayReader = new Scanner(new File(dayPath));
        // initializing arrays
        wrList = new int[recCount];
        timeList = new String[recCount];
        dayList = new String[recCount];
        // arbitrary high value as initial value for comparison to find the lowest value
        lowestWr = 100000;
        lowest = -1;
        min = newMin;
        for(int i = 0; i < recCount; i++) {
            int wrRec = Integer.parseInt(wrReader.next());
            wrList[i] =  wrRec;
            timeList[i] = hourReader.next();
            dayList[i] = dayReader.next();
            if (wrRec < lowestWr && wrRec > min) {
                lowestWr = wrRec;
                lowest = i;
            }
        }
        if (lowest == 0)
            return -1;
        return lowest;
    }

    // method for finding best time on a given day
    private static int dataReader(String day) throws FileNotFoundException{
        if (day.length()>3)
            day = getDayShort(day.toLowerCase());
        // building scanner objects to read text files
        Scanner wrReader = new Scanner(new File(wrPath));
        Scanner hourReader = new Scanner(new File(hourPath));
        Scanner dayReader = new Scanner(new File(dayPath));
        // initializing arrays
        wrList = new int[recCount];
        timeList = new String[recCount];
        dayList = new String[recCount];
        // arbitrary high value as initial value for comparison to find the lowest value
        lowestWr = 100000;
        lowest = -1;
        min = 0;
        // looping through
        for(int i = 0; i < recCount; i++) {
            int wrRec = Integer.parseInt(wrReader.next());
            wrList[i] =  wrRec;
            timeList[i] = hourReader.next();
            dayList[i] = dayReader.next();
            if (wrRec < lowestWr && wrRec > min && (dayList[i].compareToIgnoreCase(day)==0)) {
                lowestWr = wrRec;
                lowest = i;
            }
        }
        return lowest;
    }

    // String converter methods for accepting and comparing different day params
    private static String getDayLong(String arg){
        return switch (arg) {
            case "Mon", "mon" -> "monday";
            case "Tue", "tue" -> "tuesday";
            case "Wed", "wed" -> "wednesday";
            case "Thu", "thu" -> "thursday";
            case "Fri", "fri" -> "friday";
            case "Sat", "sat" -> "saturday";
            case "Sun", "sun" -> "sunday";
            default -> "Invalid arg";
        };
    }

    private static String getDayShort(String arg){
        return switch (arg.replaceAll(" ","")) {
            case "monday", "Monday" -> "Mon";
            case "tuesday", "Tuesday" -> "Tue";
            case "wednesday", "Wednesday" -> "Wed";
            case "thursday", "Thursday" -> "Thur";
            case "friday", "Friday" -> "Fri";
            case "saturday", "Saturday" -> "Sat";
            case "sunday", "Sunday" -> "Sun";
            default -> "Invalid arg";
        };
    }

    // Time writer method
    private static String getTime(String arg){
        int hour = Integer.parseInt(arg.substring(0,2));
        String min = arg.substring(3,5);
        String t = "AM";
        if (hour>12){
            hour = hour%12;
            t = "PM";
        }
        return hour + ":" + min + " "+t;
    }
}