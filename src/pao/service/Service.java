package pao.service;

import pao.entity.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Service {

    public void adaugaSpectacol(Program p, int ziua, Spectacol spectacol){
        ArrayList<Spectacol>[] program = p.getProgram();
        program[ziua].add(spectacol);
        p.sorteazaProgramZi(ziua);
    }

    public void eliminaSpectacol(Program p, String numeSpectacol){
        ArrayList<Spectacol>[] program = p.getProgram();
        for (int i = 1 ; i < 8 ; i++){
            for (int j = 0; j < program[i].size(); j++)
                if (program[i].get(j).getDenumire().equals(numeSpectacol)) {
                    program[i].remove(j);
                    p.sorteazaProgramZi(i);
                    break;
                }
        }
    }

    public void afiseazaProgram(Program p){
        ArrayList<Spectacol>[] program = p.getProgram();
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

    public void afiseazaProgramZi(Program p, int ziua){
        ArrayList<Spectacol>[] program = p.getProgram();
        System.out.println(program[ziua]);
    }

    public String[] afiseazaSaliDisponibile(Program p, String teatru){        //afiseaza salile din programul curent care se afla in teatrul dat ca param
        ArrayList<Spectacol>[] program = p.getProgram();
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
            HashMap<Bilet, Integer> bilete = c.getBilete();
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
        HashMap<Bilet, Integer> bilete = c.getBilete();
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
        HashMap<Bilet, Integer> bilete = c.getBilete();
        Spectacol spectacol = bilet.getSpectacol();
        if (verificaDisponibilitateBilete(spectacol, cantitate) != -1){
            bilete.put(bilet, cantitate);
            int numarBileteVandute = spectacol.getNumarBileteVandute() + cantitate;
            spectacol.setNumarBileteVandute(numarBileteVandute);
        }
        else System.out.println("Numar de bilete indisponibil!");
    }

    public void eliminaBilet(Cos c, Spectacol s){
        HashMap<Bilet, Integer> bilete = c.getBilete();
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

    //public schimbaNumarBilete

    public int verificaDisponibilitateBilete(Spectacol s, int cantitate){
        int numarTotalBilete = s.getLocatie().getCapacitateMaxima();
        int numarBileteVandute = s.getNumarBileteVandute();
        if (numarTotalBilete - numarBileteVandute > cantitate){
            return numarTotalBilete - numarBileteVandute;
        }
        return -1;
    }
}
