package com.example.domainselector;

public class UserInfo {

    public String Bdate;
    public String Email;
    public String Fname;
    public String Lname;
    public String Mob;
    public String Uname;
    public String ImageURL;

    public UserInfo()
    {

    }
    public UserInfo(String bdate, String email, String fname, String lname, String mob, String uname,String URL) {
        Bdate = bdate;
        Email = email;
        Fname = fname;
        Lname = lname;
        Mob = mob;
        Uname = uname;
        ImageURL=URL;
    }
}
