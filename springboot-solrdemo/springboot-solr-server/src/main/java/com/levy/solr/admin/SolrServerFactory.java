package com.levy.solr.admin;

import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

@Component
@Log
public class SolrServerFactory {

    @Value("${solr.remoteMode}")
    private boolean remoteMode;
    @Value("${solr.instanceDir}")
    private String instanceDir;
    @Value("${solr.host}")
    private String host;
    @Value("${solr.dataDir}")
    private String dataDir;

    public HttpSolrServer createCore(String coreName, String schemaCoreName) throws IOException, SolrServerException {
        long beginT = System.currentTimeMillis();
        log.info(String.format("Creating core %s...", coreName));
        HttpSolrServer server = new HttpSolrServer(host);
        if (!remoteMode) {
            CoreAdminRequest.createCore(coreName, instanceDir, server, null,
                    schemaCoreName, dataDir + File.separator + coreName, null);
        }
        long creationTime = System.currentTimeMillis() - beginT;
        log.info(String.format("Core %s created in %s milliseconds", coreName, creationTime));
        return server;
    }
}
