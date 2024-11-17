package oop;

import java.util.Scanner;

public class VastaseMänguVäli extends mänguVäli {
    private int mänguväljapikkus;

    public VastaseMänguVäli() {
    }
    public void setMänguväljapikkus(int mänguväljapikkus) {
        this.mänguväljapikkus = mänguväljapikkus;
    }
    public int getMänguväljapikkus() {
        return this.mänguväljapikkus;
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
        for (int row = 0; row < a; row++){
            System.out.print(row + " ");
            for (int col = 0; col < a; col++) {
                if (this.getMänguväli()[row][col] == 'M'
                        || this.getMänguväli()[row][col] == 'X'
                        || this.getMänguväli()[row][col] == '0'){
                    System.out.print(" " + getMänguväli()[row][col] + " ");
                }
                else {
                    System.out.print(" " + 'M' + " ");
                }
            }
            System.out.println();
        }
    }


    @Override
    // mängija saab valida, millist ruutu "pommitada"
    // vastavalt sisendile muudetakse isendivälja (st
    // märgitakse mänguväljal ruut kas 'x' või '0' (pihtas, möödas))
    public void pommita(){
        Scanner pomm = new Scanner(System.in);
        int rida, veerg;
        while (true) {
            System.out.println("Vali rida, mida soovid tabada: ");
            int rida = pomm.nextInt();
            if (rida >=0 && rida < getMänguväljapikkus()) {
                break;
            } else {
                System.out.println("Proovi uuesti, sisestasid arvu valesti: ");
            }
        }
        while (true) {
            System.out.println("Vali veerg, mida soovid tabada: ");
            veerg = pomm.nextInt();
            if (veerg >=0 && veerg < getMänguväljapikkus()) {
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
