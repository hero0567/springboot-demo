package com.levy.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
@EnableSwagger2
public class SolrController {

    @Autowired
    private SolrClient client;

    private final String SOLR_CORE_NAME = "collection1";


    @PostMapping("/insert")
    public String insert(String message) throws IOException, SolrServerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateString = sdf.format(new Date());
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", dateString);
            doc.setField("text", message);
            client.add("itaem", doc);
            client.commit("itaem");
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    @GetMapping("/get/{id}")
    public String getDocumentById(@PathVariable String id) throws SolrServerException, IOException {
        SolrDocument document = client.getById("itaem", id);
        System.out.println(document);
        return document.toString();

    }


    @DeleteMapping("/delete/{id}")
    public String getAllDocuments(@PathVariable String id) {
        try {
            client.deleteById("itaem", id);
            client.commit("itaem");
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @DeleteMapping("deleteAll")
    public String deleteAll() {
        try {

            client.deleteByQuery("itaem", "*:*");
            client.commit("itaem");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    @PutMapping("/update")
    public String update(String id, String message) throws IOException, SolrServerException {
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", id);
            doc.setField("text", message);

            /*
             * 如果 spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 itaem 这个参数 下面都是一样的 即
             * client.commit();
             */
            client.add("itaem", doc);
            client.commit("itaem");
            return doc.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    @GetMapping("/get/all")
    public Map<String, Object> getAll()
            throws SolrServerException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }


    @GetMapping("/select/{q}/{page}/{size}")
    public Map<String, Object> select(@PathVariable String q, @PathVariable Integer page, @PathVariable Integer size)
            throws SolrServerException, IOException {
        SolrQuery params = new SolrQuery();

        // 查询条件
        params.set("q", q);

        // 排序
        params.addSort("id", SolrQuery.ORDER.desc);
        // 分页
        params.setStart(page);
        params.setRows(size);

        // 默认域
        params.set("df", "text");

        // 只查询指定域
        params.set("fl", "id,text");

        // 开启高亮
        params.setHighlight(true);
        // 设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        // 设置后缀
        params.setHighlightSimplePost("</span>");

        // solr数据库是 itaem
        QueryResponse queryResponse = client.query("itaem", params);
        SolrDocumentList results = queryResponse.getResults();

        // 数量，分页用
        long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）

        // 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("data", highlight);
        return map;

    }

}
