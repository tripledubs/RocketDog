package edu.uco.sdd.rocketdog.controller;

public class KeyMappingContext {

    private KeyMapping keyMapping;

    public KeyMappingContext() {
        keyMapping = new DefaultKeyMapping();
    }

    public void setKeyMapping(KeyMapping newKeyMapping) {
        keyMapping = newKeyMapping;
    }

    public KeyMapping getKeyMapping() {
        return keyMapping;
    }
}
