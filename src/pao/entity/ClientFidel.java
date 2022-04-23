package pao.entity;

public class ClientFidel extends Client{
    private float discount;

    public ClientFidel(String nume, String prenume, String numarDeTelefon, String email, int discount) {
        super(nume, prenume, numarDeTelefon, email);
        this.discount = discount;
    }

    public ClientFidel(String nume, String prenume, String email, int discount) {
        super(nume, prenume, email);
        this.discount = discount;
    }

    @Override
    public float getDiscount() {
        return discount;
    }
}
