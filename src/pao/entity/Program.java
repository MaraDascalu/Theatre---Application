package pao.entity;

import pao.service.Audit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private List<Spectacol>[] program = new ArrayList[8];          //pentru fiecare zi a saptamanii programez niste spectacole
    private Audit audit;

    public Program(LocalDate dataInceput, LocalDate dataSfarsit) {
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        for (int i = 1 ; i < 8 ; i++)
        {
            program[i] = new ArrayList<>();
        }
        audit = Audit.getInstance("data/Audit.csv");
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }

    public List<Spectacol>[] getProgram() {
        return program;
    }

    public void sorteazaProgram(){
        for (int i = 1 ; i < 8 ; i ++){
            Collections.sort(program[i], new SpectacolComparator());
        }
    }

    public void sorteazaProgramZi(int ziua){
        Collections.sort(program[ziua], new SpectacolComparator());
    }

    public Spectacol returneazaSpectacol(String denumire){
        audit.scriereFisierAudit();
        for(int i = 1; i < 8 ; i++)
        {
            for (int j = 0 ; j < program[i].size(); j++){
                Spectacol spectacol = program[i].get(j);
                if (spectacol.getDenumire().equalsIgnoreCase(denumire))
                    return new Spectacol(spectacol.getDenumire(), spectacol.getOraInceput(), spectacol.getDurata(), spectacol.getLocatie(), spectacol.getPret(), spectacol.getGen());
            }

        }
        return null ;
    }

}
