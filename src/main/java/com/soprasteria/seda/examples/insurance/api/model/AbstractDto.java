package com.soprasteria.seda.examples.insurance.api.model;

import java.io.Serializable;

public abstract class AbstractDto implements Serializable {
    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
