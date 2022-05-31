package pao.service;

import pao.entity.Actor;
import pao.entity.Spectacol;

import java.util.*;

public class ActorService {

    private List<Actor> actori;
    private Map<Spectacol, List<Actor>> actoriSpectacole;
    private ReadData input;

    public ActorService() {
        
        input = ReadData.getInstance();
        actori = new ArrayList<>(input.citesteActori());
        actoriSpectacole = new LinkedHashMap<>(input.citesteActoriiSpectacolelor());
    }

    public Actor gasesteActorDupaNume (String nume) {
        for (Actor actor : actori){
            if (actor.getNume().equalsIgnoreCase(nume)){
                return actor;
            }
        }
        return null;
    }

    public Map<Spectacol, List<Actor>> getActoriSpectacole() {
        return actoriSpectacole;
    }
}
