package me.kalali.books.async.ch4;


import java.io.FileInputStream;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masoud
 */
public class ParserDemo {
 
    public static void main(String[] args) throws Exception  {
        FileInputStream booksInputfile = new FileInputStream("book.json");
        try{
            JsonParser parser = Json.createParser(booksInputfile);
            Event event = null;
            while(parser.hasNext()) {
                event = parser.next();
                if(event == Event.KEY_NAME && "details".equals(parser.getString())) {
                    event = parser.next();
                    break;
                }
            }
            while(event != Event.END_OBJECT) {
                switch(event) {
                    case KEY_NAME: {
                        System.out.print(parser.getString());
                        System.out.print(" = ");
                        break;
                    }
                    case VALUE_NUMBER: {
                        if(parser.isIntegralNumber()) {
                            System.out.println(parser.getInt());
                        } else {
                            System.out.println(parser.getBigDecimal());
                        }
                       break;
                    }
                    case VALUE_STRING: {
                        System.out.println(parser.getString());
                        break;
                    }
                    default: {
                    }
                }
                event = parser.next();
            }
        }
        catch(Exception ex){
            
        }
    }
 
}
