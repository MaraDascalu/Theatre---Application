package pao;

import pao.service.*;
import pao.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

//        creare service-uri
        CosService cosService = new CosService();
        ProgramService programService = new ProgramService();
        SpectacolService spectacolService = new SpectacolService();
        ClientService clientService = new ClientService();
        ActorService actorService = new ActorService();
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
                clientService.creareTabel();
                clientService.adaugaClient(nume, prenume, nrDeTel, email);

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
                    System.out.println("4) filtreaza spectacolele dupa durata");
                    System.out.println("5) afiseaza cosul de cumparaturi");
                    System.out.println("6) modifica biletele din cos");     //TODO
                    System.out.println("7) iesi");

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
                        if (spectacol != null)
                            cosService.adaugaBilet(cos, spectacol, numar);
//                        cosService.afiseazaCos(cos);
                    } else if (raspuns.startsWith("4")) {
                        System.out.println();
                        System.out.println("Introduceti durata dorita (in minute):");
                        int durata = scanner.nextInt();
                        List<Spectacol> spectacoleFiltrate = spectacolService.filtreazaSpectacoleDupaDurata(spectacole, durata);
                        writeData.afiseazaObiecte(spectacoleFiltrate);
                    } else if (raspuns.startsWith("5")) {
                        System.out.println();
                        System.out.println("Continutul cosului dumneavoastra: ");
                        cosService.afiseazaCos(cos);
                    } else if (raspuns.startsWith("6")) {
                        System.out.println();
                        System.out.println("Introduceti numele spectacolului: ");
                        String denumire = scanner.nextLine();
                        System.out.println("Introduceti numarul de bilete dorit: ");
                        int nrBilete = scanner.nextInt();
                        cosService.schimbaNumarulBiletelor(cos, spectacolService.gasesteSpectacolInProgram(denumire, program), nrBilete);

                    } else if (raspuns.startsWith("7")) {
                        System.out.println();
                        int totalDePlata = cosService.afiseazaTotalPlata(cos);
                        int numarBilete = 0;
                        if (totalDePlata != 0){
                            Map<Bilet, Integer> bilete = cos.getBilete();
                            for (Bilet b : bilete.keySet()) {
                                numarBilete+=bilete.get(b);
                            }
                        }
                        else {
                            System.out.println("Momentan cosul dumneavoastra de cumparaturi este gol!");
                        }
                        if (numarBilete >= 15) {
                            ClientFidel cf = new ClientFidel(client.getNume(), client.getPrenume(), client.getNumarDeTelefon(), client.getEmail());
                            clientService.modificaReducereClient(cf.getNume(), cf.getDiscount());
                            cos.setClient(cf);
                        }

                        if (totalDePlata != 0) {
                            if (numarBilete >= 15)
                                System.out.println("Aveti de achitat: " + cosService.aplicaReducere(cos));
                            else System.out.println("Aveti de achitat: " + totalDePlata);
                        }
                        clientService.stergeClient(client.getNume());
                        System.out.println("Multumim de vizita!");
                        break;
                    }
                }
            }
        } else System.out.println("Va asteptam sa va razganditi si sa reveniti pe platfoma noastra! :)");

    }
}


