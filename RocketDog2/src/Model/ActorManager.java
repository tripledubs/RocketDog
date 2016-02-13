/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sophia
 * keep track of actors and hero that have been created
 */
public class ActorManager {
    // the ArrayList stores data relevant to adding, removing or resetting actor objects
    
    private final List<Actor> CURRENT_ACTOR=new ArrayList<>();
    private final List<Actor> COLLIDE_CHECKLIST=new ArrayList<>();
    //hash set to store the removed actors
    private final Set<Actor> REMOVED_ACTORS=new HashSet<>();
    
    public List<Actor> getCurrentActor(){
        return CURRENT_ACTOR;
    }
    public void addCurrentActor(Actor... actors){
        CURRENT_ACTOR.addAll(Arrays.asList(actors));
    }
    public void removeCurrentActor(Actor... actors){//clear the list
        CURRENT_ACTOR.removeAll(Arrays.asList(actors));
    }
    public void resetCurrentActor(){
        CURRENT_ACTOR.clear();
    }
    public List getCollideChecklist(){
        return COLLIDE_CHECKLIST;
    }
    public void resetCollideChecklist(){
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(CURRENT_ACTOR);
    }
    public Set getRemovedActors(){
        return REMOVED_ACTORS;
    }
    public void addToRemovedActors(Actor... actors){
        if(actors.length>1){
            REMOVED_ACTORS.addAll(Arrays.asList((Actor[]) actors));
        }
        else{REMOVED_ACTORS.add(actors[0]);}
    }
}
