package pao;

import pao.service.*;
import pao.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

//        creare service-uri
        ActorService actorService = new ActorService();
        CosService cosService = new CosService();
        LocatieService locatieService = new LocatieService();
        ProgramService programService = new ProgramService();
        SpectacolService spectacolService = new SpectacolService();
        WriteData writeData = WriteData.getInstance();

//        preluare lista de spectacole din csv-ul de intrare
        List<Spectacol> spectacole = spectacolService.getSpectacole();

//        crearea unui program
        LocalDate dataInceput = LocalDate.of(2022, 4, 28);
        LocalDate dataSfarsit = dataInceput.plusDays(30);
        Program program = new Program(dataInceput, dataSfarsit);

        programService.adaugaSpectacol(program, 1, spectacole.get(1));
        programService.adaugaSpectacol(program, 1, spectacole.get(0));
        programService.adaugaSpectacol(program, 2, spectacole.get(2));
        programService.adaugaSpectacol(program, 3, spectacole.get(3));
        programService.adaugaSpectacol(program, 4, spectacole.get(4));

        System.out.println("Bun venit! Doriti sa vedeti programul pentru aceasta saptamana? Da/Nu");
        Scanner scanner = new Scanner(System.in);
        String raspuns = scanner.nextLine();

        if (raspuns.equalsIgnoreCase("da")) {
            System.out.println();
            System.out.println("PROGRAM");
            programService.afiseazaProgram(program);

            System.out.println();
            System.out.println();
            System.out.println("Pentru a putea continua, vom avea nevoie de cateva date personale. Sunteti de acord sa continuati? Da/Nu");
            raspuns = scanner.nextLine();
            if (raspuns.equalsIgnoreCase("da")) {
                String output = "";
                System.out.println("Multumim pentru confirmare!!");
                System.out.println();
                System.out.println("Va rugam sa introduceti numele de familie: ");
                String nume = scanner.nextLine();
                output = output.concat("Nume: " + nume + "\n");
                System.out.println("Va rugam sa introduceti prenumele: ");
                String prenume = scanner.nextLine();
                output = output.concat("Prenume: " + prenume + "\n");
                System.out.println("Va rugam sa introduceti numarul de telefon (optional): ");
                String nrDeTel = scanner.nextLine();
                if (nrDeTel != "") {
                    output = output.concat("Numar de telefon: " + nrDeTel + "\n");
                }
                System.out.println("Va rugam sa introduceti emailul: ");
                String email = scanner.nextLine();
                output = output.concat("Email: " + email);
                writeData.scrieDateUtilizator(output);

                System.out.println();
                Client client = new Client(nume, prenume, email);
                if (nrDeTel != "") {
                    client.setNumarDeTelefon(nrDeTel);
                }
                Cos cos = new Cos(client);

                while (true) {
                    System.out.println();
                    System.out.println("In continuare, va rugam sa alegeti una dintre urmatoarele optiuni: ");
                    System.out.println("1) afiseaza programul intreg al unei zile");
                    System.out.println("2) afiseaza informatii despre un spectacol");
                    System.out.println("3) cumpara bilete la un spectacol");
                    System.out.println("4) afiseaza cosul de cumparaturi");
                    System.out.println("5) modifica biletele din cos");
                    System.out.println("6) iesi");

                    raspuns = scanner.nextLine();
                    if (raspuns.startsWith("1")) {
                        System.out.println();
                        System.out.println("Introduceti ziua: ");
                        String ziua = scanner.nextLine();
                        programService.afiseazaProgramZi(program, ziua);
                    } else if (raspuns.startsWith("2")) {
                        System.out.println();
                        System.out.println("Introduceti numele spectacolului: ");
                        String denumire = scanner.nextLine();
                        spectacolService.afiseazaInformatiiSpectacol(denumire, program);
                    } else if (raspuns.startsWith("3")) {
                        System.out.println();
                        System.out.println("Introduceti numele spectacolului: ");
                        String denumire = scanner.nextLine();
                        System.out.println("Introduceti numarul de bilete dorite: ");
                        int numar = scanner.nextInt();
                        Spectacol spectacol = program.returneazaSpectacol(denumire);
                        System.out.println(spectacol);
                        cosService.adaugaBilet(cos, spectacol, numar);
//                        cosService.afiseazaCos(cos);
                    } else if (raspuns.startsWith("4")) {
                        System.out.println();
                        System.out.println("Continutul cosului dumneavoastra: ");
                        cosService.afiseazaCos(cos);
                    } else if (raspuns.startsWith("6")) {
                        System.out.println();
                        int totalDePlata = cosService.afiseazaTotalPlata(cos);
                        if (totalDePlata != 0) {
                            System.out.println("Aveti de achitat: " + totalDePlata);
                        }
                        System.out.println("Multumim de vizita!");
                        break;
                    }
                }
            }
        } else System.out.println("Va asteptam sa va razganditi si sa reveniti pe platfoma noastra! :)");

    }
}


