package org.mdn.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * This is the default class that will load all the spring related
 * configurations.
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan({ "com.opportunity.hack*" })
@Import({ SecurityConfig.class })
public class ApplicationConfig {

}