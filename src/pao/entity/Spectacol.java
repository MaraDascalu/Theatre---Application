package pao.entity;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Spectacol {

    private String denumire;
    private LocalTime oraInceput;
    private int durata;
    private int numarBileteVandute;
    private Locatie locatie;
    private Actor[] actori;
    private int pret;
    private Genul gen;

    public Spectacol(String denumire, LocalTime oraInceput, int durata, Locatie locatie, int pret, Genul gen) {
        this.denumire = denumire;
        this.durata = durata;
        this.oraInceput = oraInceput;
        this.numarBileteVandute = 0;
        this.locatie = locatie;
        this.actori = new Actor[15];
        this.pret = pret;
        this.gen = gen;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getDurata() {
        return durata;
    }

    public int getNumarBileteVandute() {
        return numarBileteVandute;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public Actor[] getActori() {
        return actori;
    }

    public int getPret() {
        return pret;
    }

    public Genul getGen() {
        return gen;
    }

    public LocalTime getOraInceput() {
        return oraInceput;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setNumarBileteVandute(int numarBileteVandute) {
        this.numarBileteVandute = numarBileteVandute;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public void setActori(Actor[] actori) {
        this.actori = actori;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setGen(Genul gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "denumire='" + denumire + '\'' +
                ", durata=" + durata +
                ", numarBileteVandute=" + numarBileteVandute +
                ", locatie=" + locatie +
                ", actori=" + Arrays.toString(actori) +
                ", pret=" + pret +
                ", gen=" + gen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spectacol)) return false;
        Spectacol spectacol = (Spectacol) o;
        return Objects.equals(getDenumire(), spectacol.getDenumire()) ;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getDenumire(), getOraInceput(), getDurata(), getNumarBileteVandute(), getLocatie(), getPret(), getGen());
        result = 31 * result + Arrays.hashCode(getActori());
        return result;
    }
}

class SpectacolComparator implements Comparator<Spectacol> {
    public int compare (Spectacol s1, Spectacol s2){
        return s1.getOraInceput().getHour() - s2.getOraInceput().getHour();
    }
}
