package oop;

import java.util.Scanner;

public class PäkapikkudePommitamine {
    public static void main(String[] args) {
        char mets = 'M';

        System.out.println("Sisesta mänguvälja pikkus: ");
        Scanner in = new Scanner(System.in);
        int mänguväljapikkus = in.nextInt();

        // loome mänguvälja ühele mängijale
        mänguVäli minu = new mänguVäli();
        minu.looMänguväli(mänguväljapikkus, mets);
        minu.prindiMänguväli();
        System.out.println();

        minu.arvutaPäkapikud();

        // kasutaja saab valida, kas paigutada päkapikud ise või lasta arvutil seda teha
        System.out.println("Kas soovid päkapikud ise paigutada? (true/false):");
        boolean isePaigutada = in.nextBoolean();

        if (isePaigutada) {
            for (int i = 0; i < minu.getPäkapikud(); i++) {
                minu.mängijaPaigutaPäkapikk();
            }
        } else {
            minu.lisapikud();
        }

        System.out.println("Sinu mänguväli:");
        minu.prindiMänguväli();

        // loome mänguvälja teisele (arvutile):
        VastaseMänguVäli sinu = new VastaseMänguVäli();
        sinu.looMänguväli(mänguväljapikkus, mets);
        //sinu.prindiMänguväli();
        System.out.println();

        sinu.arvutaPäkapikud();
        sinu.lisapikud();
        System.out.println("Selles mängus osaleb arvutil päkapikke: " + sinu.getPäkapikud());

        // Siin läheb mänguks:
        do {
            // siin pommitame vastast
            System.out.println("Päkapikke alles mul: " + minu.getPäkapikud());
            sinu.pommita();
            sinu.prindiMänguväli();

            // siin pommitab vastane meid
            System.out.println("Päkapikke alles tal: " + sinu.getPäkapikud());
            minu.pommita();
            minu.prindiMänguväli();

        }
        while ((minu.getPäkapikud() > 0) && (sinu.getPäkapikud() > 0));

        System.out.println("Mäng sai läbi!");
        System.out.println("Mul on alles päkapikke: " + minu.getPäkapikud());
        System.out.println("Arvutil on alles päkapikke: " + sinu.getPäkapikud());
        if (minu.getPäkapikud() == 0) {
            System.out.println("Kahjuks kaotasid mängu! Parem õnn järgmisel korral!");
        }
        else {
            System.out.println("Juhuuu, võitsid!");
        }

        
    }
}
