package pao.entity;

public class Bilet {
    private int id = 1;
    private Spectacol spectacol;
    private Client client;

    public Bilet( Spectacol spectacol, Client client) {
        this.id++;
        this.spectacol = spectacol;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public Client getClient() {
        return client;
    }
}
