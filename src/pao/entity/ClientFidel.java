package pao.entity;

public class ClientFidel extends Client{
    private int discount;

    public ClientFidel(String nume, String prenume, String numarDeTelefon, String email) {
        super(nume, prenume, numarDeTelefon, email);
        this.discount = 5;
    }

    @Override
    public int getDiscount() {
        return discount;
    }
}
