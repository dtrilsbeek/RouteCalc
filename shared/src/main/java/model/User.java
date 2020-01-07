package model;

public class User {
    private String url;
    private int id;
    private String name;
    private String password;

    public User() { } // Default to allow JSON Type convert

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String requestName, String requestPassword) {
        name = requestName;
        password = requestPassword;
    }

    public void setUrl(String url){
        this.url = url+"User/"+this.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("Name: %s Url: %s", name, url);
    }
}
