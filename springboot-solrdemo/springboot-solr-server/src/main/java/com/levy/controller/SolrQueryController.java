package com.levy.controller;

import com.levy.solr.search.domain.ResponseItem;
import com.levy.service.IBookService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
@EnableSwagger2
public class SolrQueryController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private IBookService iBookService;

    @GetMapping("/{domain}")
    public List<ResponseItem> getDocumentById(@PathVariable String domain,
                                              @RequestParam(name = "name", required = false) String name)
            throws IOException, SolrServerException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        return iBookService.search(domain, parameters);

    }

    @GetMapping("/{domain}/{id}")
    public List<ResponseItem> getDocumentById(@PathVariable String domain,
                                              @RequestParam(name = "id", required = false) String id,
                                              @RequestParam(name = "name", required = false) String name)
            throws IOException, SolrServerException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("name", name);
        return iBookService.search(domain, parameters);

    }
}
