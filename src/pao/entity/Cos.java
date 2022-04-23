package pao.entity;

import java.util.HashMap;

public class Cos {

    private int numarComanda = 0;
    private Client client;
    private HashMap<Bilet, Integer> bilete;

    public Cos(Client client) {
        this.numarComanda++;
        this.client = client;
        this.bilete = new HashMap<Bilet, Integer>();
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public Client getClient() {
        return client;
    }

    public HashMap<Bilet, Integer> getBilete() {
        return bilete;
    }
}
