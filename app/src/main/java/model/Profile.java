package model;

public class Profile {
    String name,mobile,mail,password,loginStatus;

    public Profile(String name,String mobile,String mail,String password,String loginStatus){
        this.name=name;
        this.mobile=mobile;
        this.mail=mail;
        this.password=password;
        this.loginStatus=loginStatus;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginStatus() {
        return loginStatus;
    }
}
