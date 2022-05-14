package pao.service;

import pao.entity.Program;
import pao.entity.Spectacol;

import java.util.List;

public class ProgramService implements IProgramService {

    private Audit audit;

    public ProgramService() {
        audit = Audit.getInstance("data/Audit.csv");
    }

    public void adaugaSpectacol(Program p, int ziua, Spectacol spectacol) {
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
        program.get(ziua).add(spectacol);
        p.sorteazaProgramZi(ziua);
    }

    public void eliminaSpectacol(Program p, String numeSpectacol){
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
        for (int i = 1 ; i < 8 ; i++){
            for (int j = 0; j < program.get(i).size(); j++)
                if (program.get(i).get(j).getDenumire().equals(numeSpectacol)) {
                    program.get(i).remove(j);
                    p.sorteazaProgramZi(i);
                    break;
                }
        }
    }


    public void afiseazaProgram(Program p){
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
        System.out.println(program.size());
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
            for (int j = 0; j < program.get(i).size(); j++){
                Spectacol spectacol = program.get(i).get(j);
                System.out.println("    " + spectacol.getOraInceput() +"    " + spectacol.getDenumire());
            }
        }
    }

    public void afiseazaProgramZi(Program p, String ziua){
        audit.scriereFisierAudit();
        List<List<Spectacol>> program = p.getProgram();
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
        if (program.get(zi).size() == 0){
            System.out.println("Din pacate nu exista niciun spectacol programat ");
        }

        for (int i = 0; i < program.get(zi).size(); i++){
            System.out.println(program.get(zi).get(i));
        }
    }
}
