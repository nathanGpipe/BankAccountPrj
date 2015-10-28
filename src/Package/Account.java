package Package;

import java.io.*;
import java.util.*;

 public abstract class Account implements Serializable{
     
     private static final long serialVersionUID = 1L;
     private int number;
     private String owner;
     private GregorianCalendar dateOpened;
     private double balance;
     
     public Account (int number, String dateOpened, String accountOwner, double balance){
         this.number = number;
         this.owner = accountOwner;
         this.balance = balance;
     
     
 }
     public void setNumber(int number){
         this.number = number;
     }
     
     public void setdate(){
         
     }

     public void setOwner(String owner){
         this.owner = owner;
     }

     public void setbalance(double balance){
         this.balance = balance;
     } 
     
     public int getNumber(){
         return number;
     }
    
     public GregorianCalendar getDate(){
         
         return dateOpened;
     }
     
     public String getOwner(){
         return owner;
     }
     
     public double getBalance(){
         return balance;
     }
     
}