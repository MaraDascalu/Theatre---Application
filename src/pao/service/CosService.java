package pao.service;

import pao.entity.Bilet;
import pao.entity.Cos;
import pao.entity.Spectacol;

import java.util.Map;

public class CosService implements ICosService{

    private  Audit audit;

    public CosService() {
        audit = Audit.getInstance("data/Audit.csv");
    }

    public int afiseazaTotalPlata(Cos c) {
        audit.scriereFisierAudit();
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
        audit.scriereFisierAudit();
        int pretTotal = afiseazaTotalPlata(c);
        return pretTotal - (c.getClient().getDiscount() / 100) * pretTotal;
    }

    public boolean afiseazaCos(Cos c){
        audit.scriereFisierAudit();
        Map<Bilet, Integer> bilete = c.getBilete();
        if (bilete.size() == 0){
            System.out.println("Momentan cosul dumneavoastra de cumparaturi este gol!");
            return false;
        }
        else {
            for (Bilet b : bilete.keySet()){
                System.out.println("specatacol: " + b.getSpectacol().getDenumire() + ", cantitate: " + bilete.get(b));
            }
            return true;
        }

    }

    public void adaugaBilet(Cos c, Spectacol s, int cantitate){
        audit.scriereFisierAudit();
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
        audit.scriereFisierAudit();
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
        audit.scriereFisierAudit();
        //TODO
    }

    public int verificaDisponibilitateBilete(Spectacol s, int cantitate){
        audit.scriereFisierAudit();
        int numarTotalBilete = s.getLocatie().getCapacitateMaxima();
        int numarBileteVandute = s.getNumarBileteVandute();
        if (numarTotalBilete - numarBileteVandute > cantitate){
            return numarTotalBilete - numarBileteVandute;
        }
        return -1;
    }
}