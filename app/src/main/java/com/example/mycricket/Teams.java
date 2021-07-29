package com.example.mycricket;

public class Teams {

    String Teamname;

    public Teams(){

    }

    public Teams(String Teamname){
        this.Teamname =  Teamname;
    }


    public String getTeamname() {
        return Teamname;
    }

    public void setTeamname(String teamname) {
        Teamname = teamname;
    }
}
