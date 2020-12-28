package me.kalali.books.async.ch4;


import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masoud
 */
class ReservationRequstReadListener implements ReadListener {
    private ServletInputStream input = null;
    private HttpServletResponse response = null;
    private AsyncContext context = null;    
    private Queue queue = new LinkedBlockingQueue();    
    ReservationRequstReadListener(ServletInputStream in, HttpServletResponse r,
            AsyncContext c) {
        this.input = in;
        this.response = r;
        this.context = c;
    }

    @Override
    public void onDataAvailable() throws IOException {
        StringBuilder sb = new StringBuilder();
        int read;
        byte b[] = new byte[1024];
        while (input.isReady() && (read = input.read(b)) != -1) {
            String data = new String(b, 0, read);
            sb.append(data);
        }
        queue.add(sb.toString());
   
    }

    @Override
    public void onAllDataRead() throws IOException {
        ServletOutputStream output = response.getOutputStream();
        WriteListener writeListener = new ResponseWriteListener(output, queue, context);
        output.setWriteListener(writeListener);            
    }

    @Override
    public void onError(Throwable t) {
        context.complete();        
    }
}