package pao.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class WriteData {

    private static WriteData instance;

    private WriteData() { }

    public static WriteData getInstance() {
        if (instance == null) {
            instance = new WriteData();
        }

        return instance;
    }

    public static void scrieDateUtilizator (String text) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("data/output.txt"))){
            buffer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void afiseazaObiecte(Collection<T> colectie){
        for (T obiect : colectie) {
            System.out.println(obiect);
        }
    }
}
