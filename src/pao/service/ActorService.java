package pao.service;

import pao.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorService {

    private List<Actor> actori;
    private ReadData input;

    public ActorService() {
        input = ReadData.getInstance();
        actori = new ArrayList<>(input.citesteActori());
    }
}
