package com.elife.mandra.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elife.mandra.Business.Services.AdminService;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);


    @Autowired
    AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        try {
            adminService.createDefaultAdmin();
        } catch (Exception e) {
            logger.error("Failed to create default admin", e);
        }
    }

}
