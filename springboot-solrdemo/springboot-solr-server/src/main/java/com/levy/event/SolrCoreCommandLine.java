package com.levy.event;

import com.levy.solr.admin.SolrServerFactory;
import com.levy.solr.importer.Importer;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@Log
public class SolrCoreCommandLine implements CommandLineRunner {

    private SolrServerFactory solrServerFactory;

    @Autowired
    public SolrCoreCommandLine(SolrServerFactory solrServerFactory){
        this.solrServerFactory = solrServerFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        log.warning("Start create core.");
        try{
            solrServerFactory.createCore("schema-demo", "schema-demo.xml");
        }catch (Exception e){
            log.warning("Core already existed.");
        }
    }

}