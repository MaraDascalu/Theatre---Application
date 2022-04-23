package pao.entity;

public class Locatie {

    private String denumire;
    private String teatru;
    private int capacitateMaxima;

    public Locatie(){}

    public Locatie(String denumire, String teatru ,int capacitateMaxima) {
        this.denumire = denumire;
        this.teatru = teatru;
        this.capacitateMaxima = capacitateMaxima;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getTeatru() {
        return teatru;
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "denumire='" + denumire + '\'' +
                ", teatru='" + teatru + '\'' +
                ", capacitateMaxima=" + capacitateMaxima +
                '}';
    }

}
