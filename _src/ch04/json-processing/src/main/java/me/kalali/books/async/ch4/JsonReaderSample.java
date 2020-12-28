package me.kalali.books.async.ch4;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
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
public class JsonReaderSample {
  public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> configs = new HashMap<String, Object>(1);        
        JsonReader  reader = Json.createReader(new FileInputStream("book.json"));        
        JsonObject book=reader.readObject();       
        String title = book.getString("title");
        int edition = book.getInt("edition");        
       
    }
}
