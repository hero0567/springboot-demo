package com.levy.solr.importer;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface Importer {

    public void importData() throws IOException, SolrServerException;
}
