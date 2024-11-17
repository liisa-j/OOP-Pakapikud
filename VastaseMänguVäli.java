package oop;

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
    /*public void pommita(){
        System.out.println("Vali rida, mida soovid tabada: ");
        Scanner pommrida = new Scanner(System.in);
        int rida = pommrida.nextInt();
        System.out.println("Vali veerg, mida soovid tabada: ");
        Scanner pommveerg = new Scanner(System.in);
        int veerg = pommveerg.nextInt();

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

    }*/

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



}



