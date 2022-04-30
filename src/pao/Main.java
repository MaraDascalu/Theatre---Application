package pao;

import pao.service.Service;
import pao.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        creare service
            Service service = new Service();

//        crearea de locatii noi
            Locatie l1 = new Locatie("Sala mica", "TNB", 70);
            Locatie l2 = new Locatie("Sala medie", "TNB", 120);
            Locatie l3 = new Locatie("Sala mare", "TNB", 300);
            Locatie l4 = new Locatie("Sala multi-media", "TNB", 50);
            Locatie l5 = new Locatie("Sala Teatru", "TNI", 150);
            Locatie l6 = new Locatie("Sala Uzina de Teatru", "TNI", 100);
            Locatie l7 = new Locatie("Sala Mare", "TNI", 500);

//        creare de noi actori
//        prostia omeneasca
            Actor a1 = new Actor("Carnau", "Iulia", "Iulia Carnau", 4);
            Actor a2 = new Actor("Galan", "Florij", "Florin Galan", 5);
            Actor a3 = new Actor("Bonta", "Ingrid", "Ingrid Bonta", 5);
//        doamne, ce zile minunate!
            Actor a4 = new Actor("Balasu", "Ruxandra", "Ruxanara Balasu", 3);
            Actor a5 = new Actor("Barsan", "Pavel", "Pavel Barsan", 5);
            Actor a6 = new Actor("Lixandroaia", "Ioana", "Ioana Lixandroaia", 3);
//        antigona
            Actor a7 = new Actor("Bibiri", "Andreea", "Bibiri Andreea", 10);
            Actor a8 = new Actor("Androne", "Anca", "Anca Androne", 8);
            Actor a9 = new Actor("Scripcaru", "Cornel", "Cornel Scripcaru", 9);
            Actor a10 = new Actor("State", "Alin", "Alin State", 10);
            Actor a11 = new Actor("Ciobanu", "Adrian", "Adrian Ciobanu", 10);
            Actor a12 = new Actor("Macaria", "Ana Ioana", "Ana Ioana Macaria", 8);
            Actor a13 = new Actor("Varlan", "Maria", "Maria Varlan", 9);
//        tache ianche si cadir
            Actor a14 = new Actor("Malaele", "Horatiu", "Horatiu Malaele", 20);
            Actor a15 = new Actor("Vasilescu", "Razvan", "Razvan Vasilescu", 23);
            Actor a16 = new Actor("Constantin", "Mihai", "Mihai Constantin", 21);
//        gaitele
            Actor a17 = new Actor("Morgenstern", "Maia", "Maia Morgenstern", 21);
            Actor a18 = new Actor("Tanase", "Carmen", "Carmen Tanase", 22);
            Actor a19 = new Actor("Rogin", "virginia", "Virginia Rogin", 22);
            Actor a20 = new Actor("Trandafir", "Adriana", "Adriana Trandafir", 20);


//        creare de spectacole noi
            LocalTime t1 = LocalTime.of(12, 30);
            LocalTime t2 = LocalTime.of(19, 00);
            LocalTime t3 = LocalTime.of(20, 00);

            Spectacol s1 = new Spectacol("Prostia omeneasca", t1, 120, l1, 15, Genul.COMEDIE);
            List<Actor> list1 = new ArrayList<>(Arrays.asList(a1, a2, a3));
            service.adaugaActori(list1, s1);
            Spectacol s2 = new Spectacol("Doamne, ce zile minunate!", t2, 100, l2, 15, Genul.DRAGOSTE);
            List<Actor> list2 = new ArrayList<>(Arrays.asList(a4, a5, a6));
            service.adaugaActori(list2, s2);
            Spectacol s3 = new Spectacol("Antigona", t2, 150, l6, 30, Genul.TRAGEDIE);
            List<Actor> list3 = new ArrayList<>(Arrays.asList(a7, a8, a9, a10, a11, a12, a13));
            service.adaugaActori(list3, s3);
            Spectacol s4 = new Spectacol("Tache, Ianche si Cadir", t3, 150, l5, 30, Genul.COMEDIE);
            List<Actor> list4 = new ArrayList<>(Arrays.asList(a14, a15, a16));
            service.adaugaActori(list4, s4);
            Spectacol s5 = new Spectacol("Gaitele", t3, 150, l3, 30, Genul.COMEDIE);
            List<Actor> list5 = new ArrayList<>(Arrays.asList(a17, a18, a19, a20));
            service.adaugaActori(list5, s5);

//        crearea unui program
            LocalDate dataInceput = LocalDate.of(2022, 3, 28);
            LocalDate dataSfarsit = dataInceput.plusDays(30);
            Program p1 = new Program(dataInceput, dataSfarsit);


            service.adaugaSpectacol(p1, 1, s2);
            service.adaugaSpectacol(p1, 1, s1);
            service.adaugaSpectacol(p1, 2, s3);
            service.adaugaSpectacol(p1, 3, s4);
            service.adaugaSpectacol(p1, 4, s5);

            System.out.println("Bun venit! Doriti sa vedeti programul pentru aceasta saptamana? Da/Nu");
            Scanner scanner = new Scanner(System.in);
            String raspuns = scanner.nextLine();

            if (raspuns.equalsIgnoreCase("da")) {
                System.out.println();
                System.out.println("PROGRAM");
                service.afiseazaProgram(p1);

                System.out.println();
                System.out.println();
                System.out.println("Pentru a putea continua, vom avea nevoie de cateva date personale. Sunteti de acord sa continuati? Da/Nu");
                raspuns = scanner.nextLine();
                if (raspuns.equalsIgnoreCase("da")) {
                    System.out.println("Multumim pentru confirmare!!");
                    System.out.println();
                    System.out.println("Va rugam sa introduceti numele de familie: ");
                    String nume = scanner.nextLine();
                    System.out.println("Va rugam sa introduceti prenumele: ");
                    String prenume = scanner.nextLine();
                    System.out.println("Va rugam sa introduceti numarul de telefon (optional): ");
                    String nrDeTel = scanner.nextLine();
                    System.out.println("Va rugam sa introduceti emailul: ");
                    String email = scanner.nextLine();

                    System.out.println();
                    Client client = new Client(nume, prenume, email);
                    if (nrDeTel == "") {
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
                            service.afiseazaProgramZi(p1, ziua);
                        } else if (raspuns.startsWith("2")) {
                            System.out.println();
                            System.out.println("Introduceti numele spectacolului: ");
                            String denumire = scanner.nextLine();
                            service.afiseazaInformatiiSpectacol(denumire, p1);
                        } else if (raspuns.startsWith("3")) {
                            System.out.println();
                            System.out.println("Introduceti numele spectacolului: ");
                            String denumire = scanner.nextLine();
                            System.out.println("Introduceti numarul de bilete dorite: ");
                            int numar = scanner.nextInt();
                            Spectacol spectacol = p1.returneazaSpectacol(denumire);
                            System.out.println(spectacol);
                            service.adaugaBilet(cos, spectacol, numar);
                            service.afiseazaCos(cos);
                        } else if (raspuns.startsWith("4")) {
                            System.out.println();
                            int totalDePlata = service.afiseazaTotalPlata(cos);
                            if (totalDePlata != 0) {
                                System.out.println("Aveti de achitat: " + totalDePlata);
                            }
                        } else if (raspuns.startsWith("6")) {
                            System.out.println("Multumim de vizita!");
                            break;
                            }
                    }


                }
            } else System.out.println("Va asteptam sa va razganditi si sa reveniti pe platfoma noastra! :)");

        }
}


