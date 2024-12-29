package oop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class VastaseMänguVäli extends mänguVäli {

    public VastaseMänguVäli() {
    }


    @Override
    // vastase mänguväljal ei prindi päkapikke nähtavalt
    // prindib mänguvälja hetkeseisuga, st prindib isendivälja
    public void prindiMänguväli(){
        int a = getMänguväli().length;
        System.out.print("   ");
        for (int i = 0; i < a; i++){
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int rida = 0; rida < a; rida++){
            System.out.print(rida + " ");
            for (int veerg = 0; veerg < a; veerg++) {
                if (this.getMänguväli()[rida][veerg] == 'M'
                        || this.getMänguväli()[rida][veerg] == 'X'
                        || this.getMänguväli()[rida][veerg] == '0'){
                    System.out.print(" " + getMänguväli()[rida][veerg] + " ");
                }
                else {
                    System.out.print(" " + 'M' + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }


    @Override
    // mängija saab valida, millist ruutu "pommitada"
    // vastavalt sisendile muudetakse isendivälja (st
    // märgitakse mänguväljal ruut kas 'x' või '0' (pihtas, möödas))
    // Elleni täiendatud versioon meetodist pommita (kus mängija ei saa
    // panna suuremat veeru- ja reanumbrit kui lubatud
    public void pommita(){
        Scanner pomm = new Scanner(System.in);
        int rida, veerg;

        while (true) {
            System.out.println("Vali rida, mida soovid tabada: ");
            rida=pomm.nextInt();
            if (rida >=0 && rida < getMänguväli().length) {
                break;
            } else {
                System.out.println("Proovi uuesti, sisestasid arvu valesti: " + getMänguväli().length);
            }
        }
        while (true) {
            System.out.println("Vali veerg, mida soovid tabada: ");
            veerg = pomm.nextInt();
            if (veerg >=0 && veerg < getMänguväli().length) {
                break;
            } else {
                System.out.println("Proovi uuesti, sisestasid arvu valesti: ");
            }
        }

        if (getMänguväli()[rida][veerg] == 'P'){
            System.out.println("Tabasid päkapikku!");
            getMänguväli()[rida][veerg] = 'X';
            setPäkapikud(getPäkapikud()-1);
        }
        else if (getMänguväli()[rida][veerg] == 'M'){
            System.out.println("Mööda! Hohoohooooo!");
            getMänguväli()[rida][veerg] = '0';
        }

        else if (getMänguväli()[rida][veerg] == 'X' || getMänguväli()[rida][veerg] == '0') {
            System.out.println("Seda ruutu juba proovisid! Vali uus!");
            this.pommita();
        }

    }


    // Funktsioon, mis salvestab mänguseisu
    @Override
    public void salvestus() throws IOException {
        BufferedWriter save = null;
        try {
            save = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Sinusalvestus.txt", false), StandardCharsets.UTF_8));

        }

        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        char[][] vaheseiv = this.getMänguväli();
        for (int i = 0; i < vaheseiv.length; i++) {
            for (int j = 0; j < vaheseiv[i].length; j++) {
                save.write(vaheseiv[i][j]);
            }
            save.write("\n");
        }
        save.write("#" + this.getPäkapikud());
        save.close();
    }


    // funktsioon, mis taastab salvestatud mänguseisu
    @Override
    public void taastamine() throws IOException {
        try (InputStream baidid = new FileInputStream("Sinusalvestus.txt");
             InputStreamReader tekst = new InputStreamReader(baidid, "UTF-8");
             BufferedReader puhverdatud = new BufferedReader(tekst)) {
            String rida = puhverdatud.readLine();
            int pikkus = getMänguväli().length;
            char[][] vaheseiv = new char[pikkus][pikkus];
            int s=0;
            while (rida != null) {
                if (rida.contains("#")) {
                    char[] jupid = rida.toCharArray();
                    this.setPäkapikud(Character.getNumericValue(jupid[1]));
                }
                else {
                    char[] r = rida.toCharArray();
                    vaheseiv[s] = r;
                    s += 1;
                }
                rida = puhverdatud.readLine();
            }

            // salvesta taastepunkt mänguväljaks:
            this.setMänguväli(vaheseiv);
        }
    }
}



