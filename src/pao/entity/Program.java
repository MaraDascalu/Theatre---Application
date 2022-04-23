package pao.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Program {
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private ArrayList<Spectacol>[] program = new ArrayList[8];          //pentru fiecare zi a saptamanii programez niste spectacole

    public Program(LocalDate dataInceput, LocalDate dataSfarsit) {
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        for (int i = 1 ; i < 8 ; i++)
        {
            program[i] = new ArrayList<Spectacol>();
        }
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }

    public ArrayList<Spectacol>[] getProgram() {
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
        for(int i = 1; i < 8 ; i++)
        {
            for (int j = 0 ; j < program[i].size(); j++){
                Spectacol spectacol = program[i].get(j);
                if (spectacol.getDenumire().equalsIgnoreCase(denumire))
                    return new Spectacol(spectacol.getDenumire(), spectacol.getOraInceput(), spectacol.getDurata(), spectacol.getLocatie(), spectacol.getPret(), spectacol.getGen());
            }

        }
        return new Spectacol("blabla", LocalTime.of(12,20), 2, new Locatie(), 5, Genul.COMEDIE ) ;
    }

}
