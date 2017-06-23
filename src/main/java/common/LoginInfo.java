package common;

import sun.rmi.runtime.Log;

/**
 * Created by echo on 17/6/10.
 */
public class LoginInfo {
    boolean isValid;
    String username;
    String password;
    String kind;


    public LoginInfo(boolean isValid, String username, String password, String kind) {
        this.isValid = isValid;
        this.username = username;
        this.password = password;
        this.kind = kind;
    }


    public LoginInfo(boolean isValid) {
        this.isValid = isValid;
    }

    public LoginInfo(){

    }


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "isValid=" + isValid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
