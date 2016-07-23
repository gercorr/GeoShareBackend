package com.geoshare;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.ws.rs.ApplicationPath;

public class ApplicationBinder extends ResourceConfig {

    public ApplicationBinder() {
        register(RequestContextFilter.class);
        packages("com.geoshare.rest");
        register(LoggingFilter.class);
    }
}