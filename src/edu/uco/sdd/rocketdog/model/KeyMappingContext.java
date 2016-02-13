package edu.uco.sdd.rocketdog.model;

public class KeyMappingContext {

    private KeyMapping keyMapping;
    private final DefaultKeyMapping defaultKeyMapping;
    private final WASDKeyMapping wasdKeyMapping;

    public KeyMappingContext(){
        defaultKeyMapping = new DefaultKeyMapping();
        wasdKeyMapping = new WASDKeyMapping();
        keyMapping = defaultKeyMapping;
    }
    public void setDefaultKeyMapping() {
        this.keyMapping = defaultKeyMapping;
    }

    public void setWASDKeyMapping() {
        this.keyMapping = wasdKeyMapping;
    }

    public KeyMapping getKeyMapping() {
        return keyMapping;
    }
}
