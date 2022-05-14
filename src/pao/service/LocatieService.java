package pao.service;

import pao.entity.Locatie;
import pao.entity.Program;
import pao.entity.Spectacol;

import java.util.ArrayList;
import java.util.List;

public class LocatieService implements ILocatieService{

    private List<Locatie> locatii;
    private ReadData input;
    private Audit audit;

    public LocatieService() {
        input = ReadData.getInstance();
        locatii = new ArrayList<>(input.citesteLocatii());
        audit = Audit.getInstance("data/Audit.csv");
    }

    public String[] afiseazaSaliDisponibile(Program p, String teatru){        //afiseaza salile din programul curent care se afla in teatrul dat ca param
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
        String[] lista = new String[10];
        int cnt = 0;
        for (int i = 1 ; i < 8; i++)
            for (int j = 0 ; j < program.get(i).size() ; j++)
                if (program.get(i).get(j).getLocatie().getTeatru() == teatru)
                    lista[cnt++] = program.get(i).get(j).getLocatie().getDenumire();
        return lista;
    }
}
