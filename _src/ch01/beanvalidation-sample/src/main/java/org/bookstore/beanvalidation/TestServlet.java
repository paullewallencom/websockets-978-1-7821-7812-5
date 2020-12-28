package org.bookstore.beanvalidation;


import org.glassfish.jersey.server.validation.ValidationError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * This is a standalone servlet client
 * @author Bhakti Mehta
 */
   @WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
   public class TestServlet extends HttpServlet {


       /**
        * Processes requests for both HTTP
        * <code>GET</code> and
        * <code>POST</code> methods.
        *
        * @param request servlet request
        * @param response servlet response
        * @throws ServletException if a servlet-specific error occurs
        * @throws java.io.IOException if an I/O error occurs
        */
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");

           PrintWriter out = response.getWriter();
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Servlet TestServlet</title>");
           out.println("</head>");
           out.println("<body>");
           out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
           javax.ws.rs.client.Client client = ClientBuilder.newClient();
            client.register(MessageBodyReaderWriter.class).register(BooksResource.class);

           WebTarget target = client.target("http://"
                   + request.getServerName()
                   + ":"
                   + request.getServerPort()
                   + request.getContextPath()
                   + "/books");

           Response response1 = target.path("13332").request(MediaType.APPLICATION_XML_TYPE).get();
          // Response response1 = target.request(MediaType.APPLICATION_XML_TYPE).get();
           out.println("<p> Here is the status of the response " + response1.getStatus());

           List<ValidationError> errors = response1.readEntity(new GenericType<List<ValidationError>>() {});
           if (errors != null) {
               out.println("<p>There was " + errors.size() + " error when validating the request");

           }
           out.println("<p>The following validation error was thrown: " + errors.get(0).getMessage());



           out.println("</body>");
           out.println("</html>");
       }

    /**
         * Handles the HTTP
         * <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP
         * <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }
   }


