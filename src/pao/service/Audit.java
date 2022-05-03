package pao.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Audit {

    private String  caleFisier;
    private static  Audit instance;

    private Audit(String caleFisier) {
        this.caleFisier = caleFisier;
    }

    public static Audit getInstance(String calefisier) {
        if (instance == null) {
            instance = new Audit(calefisier);
        }

        return instance;
    }

    public void scriereFisierAudit() {

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(caleFisier, true))){
            String numeFunctie = new Throwable().getStackTrace()[1].getMethodName();
            Timestamp oraExacta = new Timestamp(System.currentTimeMillis());
            buffer.write(numeFunctie + "," + oraExacta + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
