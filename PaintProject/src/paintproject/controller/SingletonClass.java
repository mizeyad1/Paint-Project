/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.controller;

/**
 *
 * @author dell-pc
 */
public class SingletonClass {
            
    //create an object of SingleObject
   private static SingletonClass instance = new SingletonClass();

   //make the constructor private so that this class cannot be
   //instantiated
   private SingletonClass(){}

   //Get the only object available
   public static SingletonClass getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
    
    
    
}
