package com.example.agecalculator; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
@SpringBootApplication 
public class Application { 
    public static void main(String[] args) { 
        try { 
            SpringApplication.run(Application.class, args); 
            System.out.println("Application started successfully."); 
        } catch (Exception e) { 
            System.err.println("Application failed to start."); 
            e.printStackTrace(); 
        } 
    } 
}