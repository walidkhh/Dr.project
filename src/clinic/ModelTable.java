/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic;

/**
 *
 * @author yaser
 */
public class ModelTable {
    String Username,Password,Prev;
    
    public ModelTable(String Username,String Password ,String Prev){
        this.Username= Username;
        this.Password= Password;
        this.Prev= Prev;
        
    }
    public String getUsername(){
        return Username;
    }
    public void setUsername(String Username){
        this.Username= Username;
    }
     public String getPassword(){
        return Password;
    }
    public void setPassword(String Password){
        this.Password= Password;
    }
     public String getPrev(){
        return Prev;
    }
    public void setPrev(String Prev){
        this.Prev= Prev;
    }
}
