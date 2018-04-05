package mjsquared.r2d2yrobo;

/**
 * Created by Miles on 4/3/2018.
 */

class Contacts {
    private int user_id;
    private String email;
    private String password;
    private String name;

    public Contacts(int user_id,String email, String password, String name){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name = name;

    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getUserId(){
        return this.user_id;
    }
    public void setUserId(int user_id){
        this.user_id = user_id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
