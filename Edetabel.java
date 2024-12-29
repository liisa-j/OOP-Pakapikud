package oop;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;


public class Edetabel {
    private int minuVõidud;
    private int vastaseVõidud;
    private int mängeMängitud;
    private int minuKäigud;
    private int vastaseKäigud;
    private LocalDateTime uusAeg;

    public Edetabel() {
        this.minuVõidud = 0;
        this.vastaseVõidud = 0;
        this.mängeMängitud = 0;
        this.minuKäigud = 0;
        this.vastaseKäigud = 0;
        this.uusAeg = LocalDateTime.now();
    }
    public void addMinuVõidud() {
        this.minuVõidud++;
    }
    public void addVastaseVõidud() {
        this.vastaseVõidud++;
    }
    public void addMängeMängitud() {
        this.mängeMängitud++;
    }
    public void addMinuKäigud() {
        this.minuKäigud++;
    }
    public void addVastaseKäigud() {this.vastaseKäigud++;}

    public void statistika() {
        System.out.println("Minu võidud " + minuVõidud + " ning minu käigud " + minuKäigud + ".");
        System.out.println("Vastase võidud " + vastaseVõidud + " ning tema käigud " + vastaseKäigud + ".");
        System.out.println("Kõikide mängude arv " +mängeMängitud + "." );
        System.out.println("Minu käigud " + minuKäigud);
        System.out.println("Vastase käigud " + vastaseKäigud);
        System.out.println("Edetabeli statistika alates " + uusAeg);
    }

    public void salvestaEdetabel (String failinimi) throws SisendiErind {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(failinimi))) {
            bw.write("Minu võidud " + minuVõidud + "\n");
            bw.write("Vastase võidud " + vastaseVõidud +  "\n");
            bw.write("Kõikide mängude arv " +mängeMängitud + "\n");
            bw.write("Minu käigud " +minuKäigud+ "\n");
            bw.write("Vastase käigud " +vastaseKäigud + "\n");
            bw.write("Edetabeli statistika alates " + uusAeg + "");
            System.out.println(failinimi + " failis on Edetabeli statistika edukalt salvestatud.");
        } catch (IOException e) {
            throw new SisendiErind("Tekkis viga faili salvestamisel" + e.getMessage());
        }
    }public void loeEdetabelist (String failinimi) throws SisendiErind {
        try(BufferedReader br = new BufferedReader(new FileReader(failinimi))) {
            minuVõidud = Integer.parseInt(br.readLine().replace("Minu võidud ", "").trim());
            vastaseVõidud = Integer.parseInt(br.readLine().replace("Vastase võidud ", "").trim());
            mängeMängitud = Integer.parseInt(br.readLine().replace("Kõikide mängude arv ", "").trim());
            minuKäigud = Integer.parseInt(br.readLine().replace("Minu käigud ", "").trim());
            vastaseKäigud = Integer.parseInt(br.readLine().replace("Vastase käigud ", "").trim());
            uusAeg = LocalDateTime.parse(br.readLine().replace("Edetabeli statistika alates ", "").trim());
            System.out.println(failinimi + " failist loetud Edetabeli statistika");
        } catch (FileNotFoundException e)  {
            throw new SisendiErind("Puudub sellise nimega fail (" + failinimi + ")");
        } catch (IOException e) {
            throw new SisendiErind("Viga faili lugemisel " + e.getMessage());
        }

    }public void tagasiNulli() {
        this.minuVõidud = 0;
        this.vastaseVõidud = 0;
        this.mängeMängitud = 0;
        this.minuKäigud = 0;
        this.vastaseKäigud = 0;
        this.uusAeg = LocalDateTime.now();
        System.out.println("Edetabeli statistika on kustutatud");
    }
}
