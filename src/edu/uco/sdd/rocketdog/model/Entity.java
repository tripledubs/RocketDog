package edu.uco.sdd.rocketdog.model;

import java.util.Map;

public interface Entity {
  boolean process(Map<Entity, Boolean> changedEntities);
  Level getLevel();
  void setLevel(Level level);
}
