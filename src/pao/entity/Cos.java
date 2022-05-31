package pao.entity;

import java.util.HashMap;
import java.util.Map;

public class Cos {

    private int numarComanda = 0;
    private Client client;
    private Map<Bilet, Integer> bilete;

    public Cos(Client client) {
        this.numarComanda++;
        this.client = client;
        this.bilete = new HashMap<Bilet, Integer>();
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setBilete(Map<Bilet, Integer> bilete) {
        this.bilete = bilete;
    }

    public Client getClient() {
        return client;
    }

    public Map<Bilet, Integer> getBilete() {
        return bilete;
    }
}
