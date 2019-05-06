package com.MongoServerAPP.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        mainMenu(chooseServer());
    }

    //Menu for choosing a server address
    private static String chooseServer(){
        //Create Scanner
        Scanner s = new Scanner(System.in);

        //Print UI
        System.out.println("[CONNECT TO SERVER]");
        System.out.println("1 - Join New Server");
        System.out.println("2 - Quick Join");
        System.out.println("");
        System.out.println("Choice: ");

        //Get users choice in a while loop so they have to pick a valid option.
        String choice = s.nextLine();
        while (true) {
            switch (choice) {
                case "1":
                    //Goto Newserver menu
                    return newServer();
                case "2":
                    //Retrieve past servers to choose from
                    return getPastServers();
                default:
                    //Default option
                    System.out.println("Error: Please choose a valid option");
                    break;
            }
        }
    }

    //Creating a new server and returning the value
    private static String newServer(){
        //Create scanner for input
        Scanner s = new Scanner(System.in);

        //Print UI
        System.out.println("[NEW SERVER]");
        System.out.print("Please Enter Server Name (Not Address): ");

        String serverName = s.nextLine();

        System.out.println("Please Enter Server Address: ");

        String serverAddress = s.nextLine();

        //Save server info for quick select next time
        saveServer(serverName, serverAddress);

        //Return address
        return serverAddress;
    }

    //Saving the server info for the quick select optioon
    public static void saveServer(String serverName, String serverAddress){

        try {

            //Create writer method
            BufferedWriter writer = new BufferedWriter(new FileWriter("servers.txt", true));

            //Write server name and address to file
            writer.write(serverName + "\n");
            writer.write(serverAddress+ "\n");
            writer.close();

        }
        catch (IOException e){

            //Catch any IOException errors
            System.out.println("Error: " + e);

        }

    }

    //Retrieve the servers for quick selection
    private static String getPastServers(){

        //Create array list and file
        ArrayList<Server> sList = new ArrayList<>();
        File f = new File("servers.txt");

        //Try for filenotfound error
        try {

            //Create scanner to read the file
            Scanner s = new Scanner(f);

            //Create iterator for server id
            int i = 0;

            //Loop through txt file until everything is read and assigned a server
            while(s.hasNextLine()){

                //Create server with txt file info
                Server server = new Server(i, s.nextLine(), s.nextLine());

                //Add server to the sList
                sList.add(server);

                //Increment incrementor
                i++;

            }

            //Loop through all servers and print each by server ID
            for(Server sv : sList){

                //Print servers
                System.out.println(sv.getId() + 1 + " - " + sv.getServerName() + " (" +sv.getServerAddress() + ")");

            }

            //Get users choice
            System.out.println("Choice: ");
            //Create new scanner for user input
            Scanner chs = new Scanner(System.in);
            String choice = chs.nextLine();

            //Check to make sure the choice is valid
            while(true) {
                if (Integer.valueOf(choice) <= (sList.size() - 1)) {

                    //Create return choice equal to actual id
                    int returnChoice = Integer.valueOf(choice) - 1;

                    //Look for server with the same id as the returnChoice
                    for (Server sv : sList) {

                        if (sv.getId() == returnChoice) {

                            //return the server address
                            return sv.serverAddress;

                        }

                    }

                } else {
                    System.out.println("Error: please chooce a valid option");
                }
            }
        }

        //Catch the error
        catch (FileNotFoundException e){

            //Print the error
            System.out.println("Error: File not found");

        }

        //Return the value
        return null;
    }

    //Main menu
    private static void mainMenu(String serverAddress){

    }

}
