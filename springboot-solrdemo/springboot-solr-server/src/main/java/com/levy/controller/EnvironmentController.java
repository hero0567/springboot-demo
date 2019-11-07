package com.levy.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@RestController
@RequestMapping("/env")
@EnableSwagger2
public class EnvironmentController {

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String getDocumentById() {
        return "all";
    }

    @GetMapping("/{name}")
    public String getDocumentById(@PathVariable String name) {
        String v = env.getProperty(name);
        return v;
    }
}