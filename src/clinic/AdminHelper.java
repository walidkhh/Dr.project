
package clinic;

public class AdminHelper {
    String username,password,privilege;
    int id ;
    
    
    public AdminHelper(String Username,String Password ,String Prev){
        this.username= Username;
        this.password= Password;
        this.privilege= Prev;
        
    }

    public AdminHelper(String username, String password, String privilege, int id) {
        this.username = username;
        this.password = password;
        this.privilege = privilege;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String Username){
        this.username= Username;
    }
     public String getPassword(){
        return password;
    }
    public void setPassword(String Password){
        this.password= Password;
    }
     public String getPrivilege(){
        return privilege;
    }
    public void setPrivilege(String privilege){
        this.privilege= privilege;
    }
}
