package com.soprasteria.seda.examples.insurance.events;

import java.io.Serializable;

public abstract class AbstractEvent implements Serializable {

    private final String type;

    protected AbstractEvent() {
        this.type = this.getClass().getSimpleName();
    }

    public String getType() {
        return type;
    }

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
