package com.example.dustnshine.models;

public class ChatModel {

    private int image;
    private  String clientname, clientMesage,timeReceived;

    public ChatModel(int image, String clientname, String clientMesage, String timeReceived) {
        this.image = image;
        this.clientname = clientname;
        this.clientMesage = clientMesage;
        this.timeReceived = timeReceived;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientMesage() {
        return clientMesage;
    }

    public void setClientMesage(String clientMesage) {
        this.clientMesage = clientMesage;
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }
}
