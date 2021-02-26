package org.vanbart.yamlread;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Demo 1: read yaml into a map of maps.
 */
@Slf4j
public class Main {

    private ObjectMapper mapper;

    public Main(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) throws IOException {
        new Main(new ObjectMapper(new YAMLFactory())).run(args);
    }

    private void run(String[] args) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("example.yaml");

        // blindly mapping the whole file into a map of maps:
        Map<String, Object> example = mapper.readValue(stream, Map.class);
        log.info("{}\n-----------------------", example);
        example.entrySet().forEach(entry -> {
            log.info("{} -> {}:{}", entry.getKey(), entry.getValue().getClass(), entry.getValue());
            if (entry.getValue() instanceof Map) {
                Map<String, Object> inner = (Map) entry.getValue();
                inner.entrySet().forEach(innerEntry -> {
                    log.info("... {} -> {}:{}", innerEntry.getKey(), innerEntry.getValue().getClass(), innerEntry.getValue());
                });
            }
        });
        log.info("\n");

        // reading a subtree: drill down to json node, then map into a class (Map in this instance)
        stream = getClass().getClassLoader().getResourceAsStream("example.yaml");
        final JsonNode node = mapper.readTree(stream).get("example").get("map2").get("mapmap2");
        final Map subtree = mapper.treeToValue(node, Map.class);
        log.info("subtree of yaml: {}", subtree);

        // Or into some DTO class
        AbThing abThing = mapper.treeToValue(node, AbThing.class);
        log.info("DTO object: {}", abThing);
    }

}
