package pao.service;

import pao.entity.Cos;
import pao.entity.Spectacol;

public interface ICosService {

    int afiseazaTotalPlata(Cos c);
    float aplicaReducere(Cos c);
    boolean afiseazaCos(Cos c);
    void adaugaBilet(Cos c, Spectacol s, int cantitate);
    void eliminaBilet(Cos c, Spectacol s);
    void schimbaNumarulBiletelor(Cos cos, Spectacol spectacol, int numarDorit);
    int verificaDisponibilitateBilete(Spectacol s, int cantitate);
}
