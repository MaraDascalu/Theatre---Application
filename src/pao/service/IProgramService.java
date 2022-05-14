package pao.service;

import pao.entity.*;

public interface IProgramService {

    void adaugaSpectacol(Program p , int ziua, Spectacol spectacol);
    void eliminaSpectacol(Program p, String numeSpectacol);
    void afiseazaProgram(Program p);
    void afiseazaProgramZi(Program p, String ziua);
}
