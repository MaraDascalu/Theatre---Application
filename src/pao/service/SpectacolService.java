package pao.service;

import pao.entity.Actor;
import pao.entity.Locatie;
import pao.entity.Program;
import pao.entity.Spectacol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpectacolService implements ISpectacolService{

    private  List<Spectacol> spectacole;
    private ReadData input;
    private Audit audit;

    public SpectacolService() {
        input = ReadData.getInstance();
        spectacole = new ArrayList<>(input.citesteSpectacole());
        audit = Audit.getInstance("data/Audit.csv");
    }

    public List<Spectacol> getSpectacole() {
        return new ArrayList<>(spectacole);
    }

    public Spectacol gasesteSpectacolInProgram(String denumire, Program p){
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
        for (int i = 1 ; i < 8; i++){
            for (int j = 0; j < program.get(i).size(); j++){
                if (program.get(i).get(j).getDenumire().equalsIgnoreCase(denumire))
                    return program.get(i).get(j);
            }
        }
        System.out.println("Nu exista acest spectacol in program!");
        return null;
    }

    public void afiseazaInformatiiSpectacol(String denumire, Program program){
        audit.scriereFisierAudit();
        Spectacol spectacol = gasesteSpectacolInProgram(denumire, program);
        if (spectacol != null) {
            System.out.println("Ora de inceput: " + spectacol.getOraInceput());
            System.out.println("Durata: " + spectacol.getDurata() + " minute");
            Locatie locatie = spectacol.getLocatie();
            System.out.println("Locatia:\n\t " + locatie.getDenumire() + " \n\tTeatrul " + locatie.getTeatru());
//            System.out.println("Actori: ");
//            List<Actor> actori = spectacol.getActori();
//            for (Actor actor : actori){
//                System.out.println("    " + actor.getNumeDeScena());
//            }
            System.out.println("Pret bilet: " + spectacol.getPret());
            System.out.println("Genul: " + spectacol.getGen());
        }

    }

    public List<Spectacol> filtreazaSpectacoleDupaDurata (List<Spectacol> spectacole, int durata) {
        return spectacole.stream().filter(spectacol -> (spectacol.getDurata() <= durata)).collect(Collectors.toList());
    }
}
