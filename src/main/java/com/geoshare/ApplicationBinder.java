package com.geoshare;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.geoshare.rest.GSService;
import com.geoshare.xml_loader.XmlLoader;

public class ApplicationBinder extends ResourceConfig
{

	public ApplicationBinder()
	{
		register(RequestContextFilter.class);
		register(GSService.class);
		register(XmlLoader.class);
		register(LoggingFilter.class);
	}
}