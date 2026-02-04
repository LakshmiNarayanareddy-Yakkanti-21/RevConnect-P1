package Models;

public class User {

    private long id;
    private String email;
    private String password;


    private String name;
    private String bio;
    private String location;
    private String website;


    private String accountType;

    /* ========= CONSTRUCTORS ========= */


    public User(long id, String email, String accountType) {
        this.id = id;
        this.email = email;
        this.accountType = accountType;
    }


    public User(String email, String password, String accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }



    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
