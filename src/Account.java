public class Account {
    public String accountNumber, suite, houseNumber, streetName;
    public String neighborhoodId, neighborhood, ward;
    public int assessedValue;
    public double longitude, latitude;
    public boolean garage;
    public String class1, class2, class3;
    public int class1Percent, class2Percent, class3Percent;


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
        class1 = data[14];
        class2 = data[15];
        class3 = data[16];

        // try to convert assessed value to int
        try {
            assessedValue = Integer.parseInt(data[8]);
            latitude = Double.parseDouble(data[9]);
            longitude = Double.parseDouble(data[10]);
            class1Percent = Integer.parseInt(data[11]);
            class2Percent = Integer.parseInt(data[12]);
            class3Percent = Integer.parseInt(data[13]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessed Value: " + data[8]);
            throw new RuntimeException(e);
        }
    }
}
