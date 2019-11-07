package com.levy.event;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Deprecated
public class WebServletContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(WebServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(sce.getServletContext().getServletContextName()+" init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info(sce.getServletContext().getServletContextName()+" destroy");
    }
}