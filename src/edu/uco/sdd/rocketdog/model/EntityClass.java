/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dpbjinc
 */
public class EntityClass {
  
  public enum Relationship {
    ENEMY,
    NEUTRAL,
    ALLY
  }
  
  private String id;
  private Map<EntityClass, Relationship> relationships;

  public EntityClass(String id) {
    if (id == null)
      throw new IllegalArgumentException("id", new NullPointerException());
    this.id = id;
    relationships = new HashMap<>();
  }
  
  public String getId() {
    return id;
  }
  
  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityClass) && id.equals(((EntityClass)o).getId());
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  public Relationship getRelationship(EntityClass entityClass) {
    return relationships.get(entityClass);
  }
  
  public Set<EntityClass> getAllEntityClasses(Relationship relationship) {
    Set<EntityClass> result = new HashSet<>();
    relationships.forEach((EntityClass e, Relationship r) -> {
      if (r == relationship)
        result.add(e);
    });
    return result;
  }

  public void setRelationship(EntityClass e, Relationship r) {
    if (e == null)
      throw new IllegalArgumentException("e", new NullPointerException());
    if (r != null)
      relationships.put(e, r);
    else
      relationships.remove(e);
  }
  
  public void setAllRelationships(Set<EntityClass> ec, Relationship r) {
    ec.forEach(e -> {
      relationships.put(e, r);
    });
  }
  
}
