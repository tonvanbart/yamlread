package org.vanbart.yamlread;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Simple DTO class.
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbThing {

    private String a;

    private String b;

    @JsonAlias({"c.d"})
    private String cd;

    @JsonAlias({"some.list"})
    private List<String> someList;
}
