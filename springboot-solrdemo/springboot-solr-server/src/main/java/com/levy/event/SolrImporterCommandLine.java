package com.levy.event;

import com.levy.solr.admin.SolrServerFactory;
import com.levy.solr.importer.Importer;
import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
@Log
public class SolrImporterCommandLine implements CommandLineRunner {

    private List<Importer> importers;

    @Autowired
    public SolrImporterCommandLine(List<Importer> importers){
        this.importers = importers;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Try to import " + importers.size());
        for (Importer importer : importers){
            importer.importData();
        }
    }

}