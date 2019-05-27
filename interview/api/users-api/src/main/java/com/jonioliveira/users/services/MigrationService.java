package com.jonioliveira.users.services;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MigrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationService.class);

    @Inject
    Flyway flyway;

    public void checkMigration(){
        LOGGER.info("Migration version: {}", flyway.info().current().getVersion().toString());
    }
}
