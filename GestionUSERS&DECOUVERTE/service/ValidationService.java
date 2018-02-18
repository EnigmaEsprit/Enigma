/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class ValidationService {
    public static boolean email_Validation(String email){
       boolean status = false;
    
    String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";  
        Pattern pattern = Pattern.compile(email_Pattern);
         Matcher matcher = pattern.matcher(email);
         if(matcher.matches())
         {
             status = true;
         }
         else
         {
             status = false;
         }
         return status;
    }
   public static boolean numerique_Validation(String numerique){
       char[] s = numerique.toCharArray();
       boolean testnum = true;
       for(int i=0;i<s.length;i++)
       {
           if(!Character.isDigit(s[i]))
           {
               testnum = false;
           }
       }
       return testnum;
   }
    
}
