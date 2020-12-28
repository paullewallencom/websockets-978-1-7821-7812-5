package org.bookstore.beanvalidation;
/**
 *
 *
 */
import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;

import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("books")
@ValidateOnExecution(type = ExecutableType.ALL)
public class BooksResource {

        @GET
        @Path("{isbn}")
    	@Consumes(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_XML)
        @NotNull(message="Book does not exist for the isbn requested")
        public Book getBook( @PathParam("isbn")String isbn) {
             return BooksCollection.getBook(isbn);

    	}




}