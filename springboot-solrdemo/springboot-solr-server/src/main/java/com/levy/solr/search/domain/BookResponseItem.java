package com.levy.solr.search.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;

@Getter
@Setter
@ToString
public class BookResponseItem extends ResponseItem {

    @Field
    private String id;
    @Field
    private String name;
}
