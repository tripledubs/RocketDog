package edu.uco.sdd.rocketdog.model;

import java.util.Map;

public interface Entity {

    public boolean process(Map<Entity, Boolean> changedEntities);

    public Level getLevel();

    public void setLevel(Level level);

    public void addObserver(Observer newObserver);

    public void removeObserver(Observer oldObserver);

    public void notifyObservers();
}
