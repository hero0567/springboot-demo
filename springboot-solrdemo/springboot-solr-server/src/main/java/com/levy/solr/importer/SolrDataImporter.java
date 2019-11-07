package com.levy.solr.importer;

import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class SolrDataImporter implements Importer{

    @Autowired
    private SolrClient client;

    @Override
    public void importData() {
        log.info("start solr data importer...");
    }
}