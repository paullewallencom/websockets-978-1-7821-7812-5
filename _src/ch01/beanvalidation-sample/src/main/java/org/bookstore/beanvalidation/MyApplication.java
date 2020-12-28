package org.bookstore.beanvalidation;

/**
 * This is an application which will register the JAX-RS resources
 * @author Bhakti Mehta
 */

import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationFeature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(BooksResource.class);
        classes.add(MessageBodyReaderWriter.class);
        classes.add(ValidationFeature.class);
        return classes;
    }



    public Map<String,Object> getProperties() {
        Map<String,Object> properties = new HashMap<String,Object>() ;
        //properties.put(ServerProperties.FEATURE_OUTPUT_VALIDATION_ERROR_ENTITY, true);
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        properties.put(ServerProperties.BV_FEATURE_DISABLE, false);
        return properties;
    }

}

