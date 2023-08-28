package com.example.domainselector;

public class YoutubeLinks {

    public  String getLink(String domain)
    {
        switch (domain)
        {
            case "Machine Learning":
                return "ukzFI9rgwfU";
            case "Artificial Intelligence":
                return "4jmsHaJ7xEA";
            case "Big Data":
                return "tkOwlXUaGMM";
            case "Blockchain Technology":
                return "SSo_EIwHSd4";
            case "Cloud Computing":
                return "eZLcyTxi8ZI";
            case "Cyber Security":
                return "2mh-N9_O_yI";
            case "Data Analysis":
                return "59SFeXcRpLE";
            case "Data Mining":
                return "R-sGvh6tI04";
            case "Internet of Things":
                return "LlhmzVL5bm8" ;
            case "Robotics":
                return "OOfXhz4In_w";
            case "Web Development":
                return "FXqTHsPaY0A";
                default:
                    return "not valid input";

        }
    }
}
