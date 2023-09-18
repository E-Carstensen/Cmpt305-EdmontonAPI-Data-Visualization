import java.util.HashMap;
import java.util.Map;

public class Account {
    public String accountNumber, suite, houseNumber, streetName;
    public String neighborhoodId, neighborhood, ward;
    public int assessedValue;
    public double longitude, latitude;
    public boolean garage;
    String point;

    Map<String, Integer> assessmentClasses = new HashMap<>();


    public static void main(String[] args) {
    }

    //Takes a line from csv data set and assigns values to object variables
    public void assignData(String[] data){
        // Order of data points in csv file
        // Account Number,Suite,House Number,Street Name,Garage,Neighbourhood ID,
        // Neighbourhood,Ward,Assessed Value,Latitude,Longitude,Point Location,
        // Assessment Class % 1,Assessment Class % 2,Assessment Class % 3,
        // Assessment Class 1,Assessment Class 2,Assessment Class 3
        accountNumber = data[0];
        suite = data[1];
        houseNumber = data[2];
        streetName = data[3];
        garage = data[4].equals("Y"); // Converts Y/N into bool
        neighborhoodId = data[5];
        neighborhood = data[6];
        ward =  data[7];
        point = data[11];

        // try to convert assessed value to int
        try {
            assessedValue = Integer.parseInt(data[8]);
            latitude = Double.parseDouble(data[9]);
            longitude = Double.parseDouble(data[10]);


            // Dictionary mapping an Assessment Type to a Percentage
            if(!data[15].isEmpty()){
                assessmentClasses.put(data[15], Integer.parseInt(data[12]));
            }
            if (!data[16].isEmpty()) {
                assessmentClasses.put(data[16], Integer.parseInt(data[13]));
            }
            if (!data[17].isEmpty()) {
                assessmentClasses.put(data[17], Integer.parseInt(data[14]));
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessed Value: " + e);
            throw new RuntimeException(e);
        }
    }
}
