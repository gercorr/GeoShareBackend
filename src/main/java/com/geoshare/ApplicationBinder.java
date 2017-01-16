package com.geoshare;

import com.geoshare.entities.User;
import com.geoshare.rest.UserRestService;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.geoshare.rest.NoteRestService;
import com.geoshare.xml_loader.XmlLoader;

public class ApplicationBinder extends ResourceConfig
{

	public ApplicationBinder()
	{
		register(RequestContextFilter.class);
		register(NoteRestService.class);
		register(UserRestService.class);
		register(XmlLoader.class);
		register(LoggingFilter.class);
		register(User.class);
	}
}