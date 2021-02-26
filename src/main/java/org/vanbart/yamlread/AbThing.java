package org.vanbart.yamlread;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
}
