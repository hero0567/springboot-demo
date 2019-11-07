package com.levy.solr.document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;

@Data
@Getter
@Setter
@ToString
public class BookDocument {
    @Field
    private String id;
    @Field
    private String name;
}
