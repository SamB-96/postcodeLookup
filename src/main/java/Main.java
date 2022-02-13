import dbSetup.H2DataBase;
import postcodeLookup.PostcodeLookup;
import postcodeLookup.PostcodeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String... args) throws IOException {
        H2DataBase.getInstance(); // Sets up the database with required table and data

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean checkPostcode = true;
        while (checkPostcode) {
            //Gets postcode from user
            System.out.print("Enter your postcode to check delivery options: ");
            String postcode = reader.readLine().trim().toUpperCase();

            boolean validInput = PostcodeUtils.isValidPostcode(postcode);
            while (!validInput) {
                System.out.print("\nSorry, that isn't a valid postcode.\nEnter your postcode to check delivery options: ");
                postcode = reader.readLine().trim().toUpperCase();
                validInput = PostcodeUtils.isValidPostcode(postcode);
            }

            //Looks up available deliveries
            PostcodeLookup lookup = new PostcodeLookup();
            String[] options = lookup.getValidDeliveryOptions(postcode);
            System.out.println("The available delivery options for " + postcode + " are: ");
            for (String option : options) {
                System.out.println("\t- " + option);
            }

            //Check if user wants to look up another postcode
            System.out.print("\nCheck another postcode? y/n:");
            String answer = reader.readLine().trim().toUpperCase();
            if (!answer.equals("Y") && !answer.equals("YES")) {
                checkPostcode=false;
            }
        }
        reader.close();
    }
}
