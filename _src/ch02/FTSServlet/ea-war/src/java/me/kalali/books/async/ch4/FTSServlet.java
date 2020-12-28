/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kalali.books.async.ch4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author masoud
 */
@WebServlet(name = "FTSServlet", urlPatterns = {"/FTSServlet"})
public class FTSServlet extends HttpServlet {

    @EJB
    FTSSearch ftsSearch;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Future<List<String>> wsResult = ftsSearch.search("WebSockets", 5000);
        Future<List<String>> sseResult = ftsSearch.search("SSE", 1000);

        while (!sseResult.isDone()) {
            try {
                Thread.sleep(500);
                //perform other tasks... e.g. show progress status
            } catch (InterruptedException ex) {
                Logger.getLogger(FTSServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet d</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>SSE Search result: " + sseResult.get().get(0) + "</h1>");
            while (!wsResult.isDone()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FTSServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.println("<h1>WS Search result: " + wsResult.get().get(0) + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (InterruptedException ex) {
            Logger.getLogger(FTSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FTSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

}
