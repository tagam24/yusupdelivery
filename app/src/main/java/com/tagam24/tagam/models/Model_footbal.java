package com.tagam24.tagam.models;

public class Model_footbal {

    public String getId() {
        return id;
    }

    public String id,image,image1,name,watched,liked,date,content,team1,draw,team2,team_n1,draw_n,team_n2;

    public String getImage() {
        return image;
    }

    public String getImage1() {
        return image1;
    }

    public String getName() {
        return name;
    }

    public String getWatched() {
        return watched;
    }

    public String getliked() {
        return liked;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getTeam1() {
        return team1;
    }

    public String getDraw() {
        return draw;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam_n1() {
        return team_n1;
    }
    public String getDraw_n() {
        return draw_n;
    }

    public String getTeam_n2() {
        return team_n2;
    }

    public Model_footbal(String id, String image, String image1, String name, String watched, String liked, String date, String content, String team1, String draw, String team2, String team_n1,String draw_n, String team_n2) {
        this.id = id;
        this.image = image;
        this.image1 = image1;
        this.name = name;
        this.watched = watched;
        this.liked = liked;
        this.date = date;
        this.content = content;
        this.team1 = team1;
        this.draw = draw;
        this.team2 = team2;
        this.team_n1=team_n1;
        this.draw_n=draw_n;
        this.team_n2=team_n2;
    }
}

