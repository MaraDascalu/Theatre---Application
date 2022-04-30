package pao.service;

import pao.entity.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {

    public void adaugaSpectacol(Program p, int ziua, Spectacol spectacol){
        List<Spectacol>[] program = p.getProgram();
        program[ziua].add(spectacol);
        p.sorteazaProgramZi(ziua);
    }

    public void eliminaSpectacol(Program p, String numeSpectacol){
        List<Spectacol>[] program = p.getProgram();
        for (int i = 1 ; i < 8 ; i++){
            for (int j = 0; j < program[i].size(); j++)
                if (program[i].get(j).getDenumire().equals(numeSpectacol)) {
                    program[i].remove(j);
                    p.sorteazaProgramZi(i);
                    break;
                }
        }
    }

    public Spectacol gasesteSpectacolInProgram(String denumire, Program p){
        List<Spectacol>[] program = p.getProgram();
        for (int i = 1 ; i < 8; i++){
            for (int j = 0; j < program[i].size(); j++){
                if (program[i].get(j).getDenumire().equalsIgnoreCase(denumire))
                    return program[i].get(j);
            }
        }
//        System.out.println("Nu exista acest spectacol in program!");
        return null;
    }

    public void afiseazaInformatiiSpectacol(String denumire, Program program){
        Spectacol spectacol = gasesteSpectacolInProgram(denumire, program);
        System.out.println("Ora de inceput: " + spectacol.getOraInceput());
        System.out.println("Durata: " + spectacol.getDurata() + " minute");
        Locatie locatie = spectacol.getLocatie();
        System.out.println("Locatia: sala " + locatie.getDenumire() + ", teatrul: " + locatie.getTeatru());
        System.out.println("Actori: ");
        List<Actor> actori = spectacol.getActori();
        for (Actor actor : actori){
            System.out.println("    " + actor.getNumeDeScena());
        }
        System.out.println("Pret bilet: " + spectacol.getPret());
        System.out.println("Genul: " + spectacol.getGen());
    }

    public void adaugaActori(List<Actor> lista_actori, Spectacol spectacol) {
        List<Actor> actori = spectacol.getActori();
        for (Actor actor : lista_actori){
            actori.add(actor);
        }
    }

    public void afiseazaProgram(Program p){
        List<Spectacol>[] program = p.getProgram();
        for (int i = 1; i < 8 ; i++){
            switch (i){
                case 1:
                    System.out.println("Luni");
                    break;
                case 2:
                    System.out.println("Marti");
                    break;
                case 3:
                    System.out.println("Miercuri");
                    break;
                case 4:
                    System.out.println("Joi");
                    break;
                case 5:
                    System.out.println("Vineri");
                    break;
                case 6:
                    System.out.println("Sambata");
                    break;
                case 7:
                    System.out.println("Duminica");
                    break;
            }
            for (int j = 0; j < program[i].size(); j++){
                Spectacol spectacol = program[i].get(j);
                System.out.println("    " + spectacol.getOraInceput() +"    " + spectacol.getDenumire());
            }
        }
    }

    public void afiseazaProgramZi(Program p, String ziua){
        List<Spectacol>[] program = p.getProgram();
        int zi = 0;
        switch (ziua.toUpperCase()){
            case "LUNI":
                zi = 1;
                break;
            case "MARTI":
                zi = 2;
                break;
            case "MIERCURI":
                zi = 3;
                break;
            case "JOI":
                zi = 4;
                break;
            case "VINERI":
                zi = 5;
                break;
            case "SAMBATA":
                zi = 6;
                break;
            case "DUMINICA":
                zi = 7;
                break;
        }
        if (program[zi].size() == 0){
            System.out.println("Din pacate nu exista niciun spectacol programat ");
        }

        for (int i = 0; i < program[zi].size(); i++){
            System.out.println(program[zi].get(i));
        }
    }

    public String[] afiseazaSaliDisponibile(Program p, String teatru){        //afiseaza salile din programul curent care se afla in teatrul dat ca param
        List<Spectacol>[] program = p.getProgram();
        String[] lista = new String[10];
        int cnt = 0;
        for (int i = 1 ; i < 8; i++)
            for (int j = 0 ; j < program[i].size() ; j++)
                if (program[i].get(j).getLocatie().getTeatru() == teatru)
                    lista[cnt++] = program[i].get(j).getLocatie().getDenumire();
        return lista;
    }

    public int afiseazaTotalPlata(Cos c) {
        int total = 0;
        if (afiseazaCos(c)){
            Map<Bilet, Integer> bilete = c.getBilete();
            for (Bilet b : bilete.keySet()) {
                total += b.getSpectacol().getPret() * bilete.get(b);
            }
        }
        return total;
    }

    public float aplicaReducere(Cos c){
        int pretTotal = afiseazaTotalPlata(c);
        return pretTotal - (c.getClient().getDiscount() / 100) * pretTotal;
    }

    public boolean afiseazaCos(Cos c){
        Map<Bilet, Integer> bilete = c.getBilete();
        if (bilete.size() == 0){
            System.out.println("Momentan cosul dumneavoastra de cumparaturi este gol!");
            return false;
        }
        else {
            for (Bilet b : bilete.keySet()){
                System.out.println("specatacol: " + b.getSpectacol().getDenumire() + " cantitate: " + bilete.get(b));
            }
            return true;
        }

    }

    public void adaugaBilet(Cos c, Spectacol s, int cantitate){
        Bilet bilet = new Bilet(s, c.getClient());
        Map<Bilet, Integer> bilete = c.getBilete();
        Spectacol spectacol = bilet.getSpectacol();
        if (verificaDisponibilitateBilete(spectacol, cantitate) != -1){
            bilete.put(bilet, cantitate);
            int numarBileteVandute = spectacol.getNumarBileteVandute() + cantitate;
            spectacol.setNumarBileteVandute(numarBileteVandute);
        }
        else System.out.println("Numar de bilete indisponibil!");
    }

    public void eliminaBilet(Cos c, Spectacol s){
        Map <Bilet, Integer> bilete = c.getBilete();
        for (Bilet b : bilete.keySet()){
            if (b.getSpectacol() == s)
            {
                int numarBilete = b.getSpectacol().getNumarBileteVandute() - bilete.get(b);
                b.getSpectacol().setNumarBileteVandute(numarBilete);
                bilete.remove(b);
                break;
            }
        }
    }

    public void schimbaNumarulBiletelor(Cos cos, Spectacol spectacol, int numarDorit) {
        //TODO
    }

    public int verificaDisponibilitateBilete(Spectacol s, int cantitate){
        int numarTotalBilete = s.getLocatie().getCapacitateMaxima();
        int numarBileteVandute = s.getNumarBileteVandute();
        if (numarTotalBilete - numarBileteVandute > cantitate){
            return numarTotalBilete - numarBileteVandute;
        }
        return -1;
    }
}
