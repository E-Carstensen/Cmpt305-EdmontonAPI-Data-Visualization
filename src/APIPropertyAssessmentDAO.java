import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIPropertyAssessmentDAO implements PropertyAssessmentDAO {



     public static List<Account> getByAccountId(String accountId){


         String[] args = {"account_number", accountId};

         String accts = APIQuery(args);

         return createAccounts(accts.split("\n"));



    }

    public static List<Account> getByNeighborhood(String neighborhood){

         String[] args = {"neighbourhood", neighborhood};

         String response = APIQuery(args);

         return createAccounts(response.split("\n"));

    }

    public static List<Account> getAll(){

         String[] args = {"$limit", "100"};

         String response = APIQuery(args);

        return createAccounts(response.split("\n"));

    }

    public static List<Account> getAll(int limit){
         String[] args = {"$limit", String.valueOf(limit)};

         String response = APIQuery(args);

         return createAccounts(response.split("\n"));

    }

    public static String APIQuery(String[] args){

        StringBuilder endpoint = new StringBuilder();
        endpoint.append("https://data.edmonton.ca/resource/q7d6-ambg.csv");
        for (int i = 0; i < args.length-1; i+=2){

            if(i==0){endpoint.append("?");}else{endpoint.append("&");}

            endpoint.append(args[i]).append("=")
                    .append(args[i + 1].replace(" ", "%20"));
        }

        String query = endpoint.toString();


        HttpClient client = HttpClient.newHttpClient();

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();


        }catch(Exception e){
            return e.toString();
        }
    }

    private static List<Account> createAccounts(String[] accts){

         List<Account> accounts = new ArrayList<Account>();

         for (String line : accts){

             String[] data = line.split(",", -1);

             if (data[0].equals("\"account_number\"")){continue;}

             for (int i = 0; i<data.length; i++){data[i] = data[i].replace("\"", "");}


             System.out.println(Arrays.toString(data));

             accounts.add(new Account(data));

         }

         return accounts;

    }
}
