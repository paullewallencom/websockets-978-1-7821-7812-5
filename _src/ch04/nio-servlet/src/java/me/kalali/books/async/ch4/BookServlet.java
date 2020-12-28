package me.kalali.books.async.ch4;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author masoud
 */
// enable async in the servlet
@WebServlet(urlPatterns="/book-servlet", asyncSupported=true)
public class BookServlet extends HttpServlet {
    protected void doPost(final HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        AsyncContext ac = req.startAsync();
        ac.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                event.getSuppliedResponse().getOutputStream().print("Async Operation Completed");
            }
            public void onError(AsyncEvent event) {
                System.out.println(event.getThrowable());
            }
            public void onStartAsync(AsyncEvent event) {
                System.out.println("Async Operation Started");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            public void onTimeout(AsyncEvent event) {
                System.out.println("Async Operation Timedout");
            }
        });
        ServletInputStream input = req.getInputStream();
        ReadListener readListener =  new ReservationRequstReadListener(input, res, ac );
        input.setReadListener(readListener);
    }
}
