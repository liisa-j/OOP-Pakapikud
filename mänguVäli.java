package oop;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class mänguVäli {
    private char[][] mänguväli;
    private int päkapikud = 0;
    private int mänguväljapikkus;

    // getter mänguvälja väljale
    public char[][] getMänguväli() {
        return mänguväli;
    }

    // setter mänguvälja väljale
    public void setMänguväli(char[][] mänguväli) {
        this.mänguväli = mänguväli;
    }

    // getter päkapikkuse väljale
    public int getPäkapikud() {
        return päkapikud;
    }

    // setter päkapikkude väljale
    public void setPäkapikud(int päkapikud) {
        this.päkapikud = päkapikud;
    }

    // loob algse tühja (st metsaga täidetud) mänguvälja ja muudab isendivälja
    public void looMänguväli(int mänguväljapikkus, char mets){
        this.mänguväljapikkus = mänguväljapikkus;
        char[][] mänguLaud = new char[mänguväljapikkus][mänguväljapikkus];
        for (char[] rida : mänguLaud){
            Arrays.fill(rida, mets);
        }
        setMänguväli(mänguLaud);
    }


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
                System.out.print(" " + getMänguväli()[row][col] + " ");
            }
            System.out.println();
        }
}

    // päkapikkude hulga arvutaja
    public void arvutaPäkapikud(){
        int b = getMänguväli().length;
        int päkapikke = (int) (b*b*0.15);
        this.setPäkapikud(päkapikke);
    }

    // genereeritakse päkapikkudele juhuslikud asukohad
    public void lisapikud(){
        for (int i = 0; i < getPäkapikud(); i++) {
            int rida = ThreadLocalRandom.current().nextInt(0, getMänguväli().length);
            int veerg = ThreadLocalRandom.current().nextInt(0, getMänguväli().length);
            this.mänguväli[rida][veerg] = 'P';
        }
    }


    // arvuti genereerib juhuslikult, kuhu pommitatakse
    // vastavalt sisendile muudetakse isendivälja (st
    // märgitakse mänguväljal ruut kas 'x' või '0' (pihtas, möödas))
    public void pommita(){
        int rida = ThreadLocalRandom.current().nextInt(0, getMänguväli().length);
        int veerg = ThreadLocalRandom.current().nextInt(0, getMänguväli().length);
        System.out.println("Vastane nimega Suur Paha Arvuti proovib su päkapikku tabada koordinaatidelt: " + rida + ", " + veerg);

        if (this.mänguväli[rida][veerg] == 'P'){
            System.out.println("Oh ei, ta pommis su päkapikku!");
            getMänguväli()[rida][veerg] = 'X';
            setPäkapikud(getPäkapikud()-1);
        }
        else if (this.mänguväli[rida][veerg] == 'M'){
            System.out.println("Suur paha arvuti lõi mööda! Hohoohooooo!");
            this.mänguväli[rida][veerg] = '0';
        }

        else if (this.mänguväli[rida][veerg] == 'X' || this.mänguväli[rida][veerg] == '0') {
            //System.out.println("Seda ruutu juba proovisid, rumal arvuti! Vali uus!");
            this.pommita();
        }

    }


}
