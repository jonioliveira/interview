package com.jonioliveira.users;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Users API",
                version = "1.0",
                description = "API to manage and log users",
                contact = @Contact( name = "Jóni Oliveira", email = "joni.oliveira89@gmail.com")))
public class UsersApplication extends Application {
}
