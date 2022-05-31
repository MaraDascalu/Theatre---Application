package pao.service;

import pao.entity.Cos;
import pao.entity.Spectacol;
import pao.entity.Client;

public interface ICosService {

    int afiseazaTotalPlata(Cos c);
    float aplicaReducere(Cos c);
    boolean afiseazaCos(Cos c);
    void adaugaBilet(Cos c, Spectacol s, int cantitate);
    void eliminaBilet(Cos c, String denumire);
    void schimbaNumarulBiletelor(Cos cos, Spectacol spectacol, int numarDorit);
    int verificaDisponibilitateBilete(Spectacol s, int cantitate);
}
