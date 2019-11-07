package com.levy.solr.importer;
import com.levy.solr.admin.SolrServerFactory;
import com.levy.solr.document.BookDocument;
import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log
public class BookImporter implements Importer{

    @Autowired
    private SolrClient client;
    private final String COLLECTION = "schema-demo";

    @Override
    public void importData() throws IOException, SolrServerException {
        log.info("start book importer...");
        List<BookDocument> documents = new ArrayList<>();
        BookDocument b = new BookDocument();
        b.setId("1");
        b.setName("book1");

        documents.add(b);
        client.addBeans(documents);
        client.commit();

        log.info("book import successful.");
    }
}
