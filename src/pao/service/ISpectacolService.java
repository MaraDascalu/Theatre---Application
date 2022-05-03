package pao.service;

import pao.entity.Program;
import pao.entity.Spectacol;

public interface ISpectacolService {

    void afiseazaInformatiiSpectacol(String denumire, Program program);
    Spectacol gasesteSpectacolInProgram(String denumire, Program p);
}
