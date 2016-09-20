package ongraph.com.blooddonate;
public class Person {
    //name and address string
    private String name;
    private String password;

    public Person() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}