package com.MongoServerAPP.main;

public class Server {

    //default server info
    int id;
    String serverName = "";
    String serverAddress = "";

    //Server class constructor
    public Server(int id, String serverName, String serverAddress){
        this.id = id;
        this.serverAddress = serverAddress;
        this.serverName = serverName;
    }

    //Get ID method
    public int getId() {
        return id;
    }

    //Get server name method
    public String getServerName() {
        return serverName;
    }

    //Get server address method
    public String getServerAddress() {
        return serverAddress;
    }
}
