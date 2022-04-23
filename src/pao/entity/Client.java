package pao.entity;

public class Client {
    protected String nume;
    protected String prenume;
    protected String numarDeTelefon;
    protected String email;

    public Client(String nume, String prenume, String numarDeTelefon, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.numarDeTelefon = numarDeTelefon;
        this.email = email;
    }

    public Client(String nume, String prenume, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumarDeTelefon() {
        return numarDeTelefon;
    }

    public void setNumarDeTelefon(String numarDeTelefon) {
        this.numarDeTelefon = numarDeTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getDiscount(){
        return  0;
    }
}
