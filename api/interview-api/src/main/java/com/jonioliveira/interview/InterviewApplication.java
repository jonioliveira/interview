package com.jonioliveira.interview;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Interview API",
                version = "1.0",
                description = "API to manage interviews and availability",
                contact = @Contact( name = "Jóni Oliveira", email = "joni.oliveira89@gmail.com")))
public class InterviewApplication extends Application {
}
