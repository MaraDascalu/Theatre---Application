package pao.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

import pao.entity.*;


public class ReadData {

    private static ReadData instance;

    public static ReadData getInstance() {
        if (instance == null) {
            instance = new ReadData();
        }
        return instance;
    }

    public List<Locatie> citesteLocatii() {
        List<Locatie> locatii = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader("data/Locatii.csv"))){

            String linie = buffer.readLine();
            while (linie != null){
                String[] data = linie.split(",");
                Locatie locatie = new Locatie(data[0], data[1], Integer.parseInt(data[2]));
                locatii.add(locatie);
                linie = buffer.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locatii;
    }

    public List<Actor> citesteActori() {
        List<Actor> actori = new ArrayList<>();

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/Actori.csv"))) {

            String linie = buffer.readLine();
            while (linie != null) {
                String[] data = linie.split(",");
                Actor actor = new Actor(data[0], data[1], data[2], Integer.parseInt(data[3]));
                actori.add(actor);
                linie = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actori;
    }

    public List<Spectacol> citesteSpectacole() {
        List<Locatie> locatii = citesteLocatii();
        List<Spectacol> spectacole = new ArrayList<>();

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/Spectacole.csv"))) {

            String linie = buffer.readLine();
            while (linie != null) {
                String[] data = linie.split(",");
                LocalTime t = LocalTime.of(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                Spectacol spectacol = new Spectacol(data[0], t, Integer.parseInt(data[1]), locatii.get(Integer.parseInt(data[4]) - 1), Integer.parseInt(data[5]));
                spectacole.add(spectacol);
                linie = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spectacole;
    }

    public Map<Spectacol, List<Actor>> citesteActoriiSpectacolelor() {
        List<Actor> actori = citesteActori();
        List<Spectacol> spectacole = citesteSpectacole();
        Map<Spectacol, List<Actor>> actoriSpectacole= new LinkedHashMap<>();

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/Spectacole-Actori.csv"))) {

            String linie = buffer.readLine();
            while (linie != null) {
                String[] data = linie.split(",");
                List<Actor> aux = new ArrayList<>();
                for (int i = 1 ; i < data.length; i++)
                    aux.add(actori.get(Integer.parseInt(data[i]) - 1));
                actoriSpectacole.put(spectacole.get(Integer.parseInt(data[0]) - 1), new ArrayList<>(aux));
                linie = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actoriSpectacole;
    }
}
