/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kalali.books.async.ch4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author masoud
 */
@Stateless
@LocalBean
public class FTSSearch {

    @Asynchronous
    public Future<List<String>> search(String text, int dummyWait) {        
        List<String> books = null;
        try {
           books= performSearch(text, dummyWait);
        } catch (InterruptedException e) {
            //handling exception
        }
        return new AsyncResult<List<String>>(books);        
    }
    private List<String> performSearch(String content, int dummyWait) throws InterruptedException{
        Thread.sleep(dummyWait);
        return Arrays.asList(content);
    }
}
