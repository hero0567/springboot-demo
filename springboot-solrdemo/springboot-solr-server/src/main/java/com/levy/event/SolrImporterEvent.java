package com.levy.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

@Deprecated
public class SolrImporterEvent extends ApplicationEvent {

    private static final Logger logger = Logger.getLogger(SolrImporterEvent.class);

    public SolrImporterEvent(Object source) {
        super(source);
        logger.info("SolrImporterEvent");
    }

}
