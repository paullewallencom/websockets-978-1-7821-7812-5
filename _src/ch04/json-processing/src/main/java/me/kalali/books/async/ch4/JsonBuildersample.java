package me.kalali.books.async.ch4;


import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author masoud
 */
public class JsonBuildersample {

    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<String, Object>(1);        
        JsonBuilderFactory factory = Json.createBuilderFactory(configs);
       JsonObject book=  factory.createObjectBuilder()
                .add("title", "Getting Started with WebSockets")
                .add("type", "paperack")
                .add("author", "Bhakti Mehta, Masoud Kalali")
                .add("publisher", "Packt")
                .add("publication yeah", "2013")
                .add("edition", 1)
                .build();   
       configs.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriter writer = Json.createWriterFactory(configs).createWriter(System.out);
        writer.writeObject(book);
    }
}
