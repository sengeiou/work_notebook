package com.example.my_application_test;

public class SongTestObject {
    private int id;
    private String number;
    private String songInformation;

    public SongTestObject(String number, String songInformation) {
        this.number = number;
        this.songInformation = songInformation;
    }

    public SongTestObject(int id,String number, String songInformation) {
        this.id = id;
        this.number = number;
        this.songInformation = songInformation;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getSongInformation() {
        return songInformation;
    }
}
