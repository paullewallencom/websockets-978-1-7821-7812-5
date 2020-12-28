package me.kalali.books.async.ch4;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masoud
 */
import java.util.*;
import javax.json.Json;
import javax.json.stream.*;
 
public class GeneratorDemo {
 
    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<String, Object>(1);
        configs.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonGeneratorFactory factory = Json.createGeneratorFactory(configs);
        JsonGenerator jg = factory.createGenerator(System.out);
         
        jg.writeStartObject()                    
            .write("name", "Getting Started with WebSockets")  
            .writeStartObject("details")     
                .write("type", "paperack")    
                .write("author", "Bhakti Mehta, Masoud Kalali")   
                .write("publisher","Packt")            
                .write("publication yeah", "2013")     
                .write("edition",  1)
            .writeEnd()                         
        .writeEnd()                             
        .close();
    } 
}
