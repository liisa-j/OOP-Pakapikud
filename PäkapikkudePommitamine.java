package oop;

import java.util.Scanner;

public class PäkapikkudePommitamine {
    private static void tutvustusTekst() {
        System.out.println("               Päkapikkude pommitamise mängujuhend:");
        System.out.println("1. Mängu alustades tuleb valida mänguvälja suurus (alates 3 kuni 10). ");
        System.out.println("2. Järgmisena kuvatakse soovitud mänguväli (M-mets ehk pommitamata ala).");
        System.out.println("3. Samuti annab mäng teada, palju päkapikke on metsas peidus mängijatel. ");
        System.out.println("4. Nüüd tuleb otsustada, millist asukohta soovitakse pommitada.");
        System.out.println("4. Pihtasaamisel kuvatakse mänguväljal X, möödapanekul kuvatakse O.");
        System.out.println("5. Võidab see mängija, kes saab vastase päkapikkudele esimesena pihta.");
        System.out.println("               Nüüd kui kõik on selge, saab alustada mängimist!");
    }
    public static void main(String[] args) {
        tutvustusTekst();
        char mets = 'M';
        Edetabel info = new Edetabel();

        //  loome mänguvälja, millel on juures sobiva sisendi kontroll
        while (true) {
            System.out.println("Sisesta mänguvälja pikkus: ");
            Scanner in = new Scanner(System.in);
            int mänguväljapikkus = in.nextInt();
            while (mänguväljapikkus < 3 || mänguväljapikkus > 10) {
                    System.out.println("Proovi uuesti, mänguvälja vahemik on 3-10 ruutu");
                    System.out.println("Sisesta mänguvälja pikkus: ");
                    mänguväljapikkus = in.nextInt();
            }

            // loome mänguvälja ühele mängijale
            mänguVäli minu = new mänguVäli();
            minu.looMänguväli(mänguväljapikkus, mets);
            minu.prindiMänguväli();
            System.out.println();
    
            Scanner sca = new Scanner(System.in);
            int minuvalik = 0;
            while (minuvalik !=1 && minuvalik !=2) {
                System.out.println("Kas soovid valida ise päkapikkude asukohti või panna paika juhuslikult: (1/2");
                if (sca.hasNextInt()) {
                    minuvalik = sca.nextInt();

                    if (minuvalik == 1) {
                        minu.arvutaPäkapikud();
                        minu.valinPäkapikud();
                    }else if (minuvalik == 2) {
                        minu.arvutaPäkapikud();
                        minu.lisapikud();
                        System.out.println("Selles mängus osaleb päkapikke: " + minu.getPäkapikud());
                    }else {
                        System.out.println("Proovi uuesti, 1 - valin ise, 2 - juhuslik paigutus");
                    }
                } else {
                      System.out.println("Ebasobiv sisend.");
                      sca.next();
                }
            }
    
            // loome mänguvälja teisele (arvutile):
            VastaseMänguVäli sinu = new VastaseMänguVäli();
            sinu.setMänguväljapikkus(mänguväljapikkus);
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
                info.addMinuKäigud();
                sinu.pommita();
                sinu.prindiMänguväli();
    
                // siin pommitab vastane meid
                System.out.println("Päkapikke alles tal: " + sinu.getPäkapikud());
                info.addVastaseKäigud();
                minu.pommita();
                minu.prindiMänguväli();
    
            }
            while ((minu.getPäkapikud() > 0) && (sinu.getPäkapikud() > 0));
    
            System.out.println("Mäng sai läbi!");
            System.out.println("Mul on alles päkapikke: " + minu.getPäkapikud());
            System.out.println("Arvutil on alles päkapikke: " + sinu.getPäkapikud());
            if (minu.getPäkapikud() == 0) {
                System.out.println("Kahjuks kaotasid mängu! Parem õnn järgmisel korral!");
                info.addVastaseVõidud();
            }
            else {
                System.out.println("Juhuuu, võitsid!");
                info.addMinuVõidud();
            }
            info.statistika();

            System.out.println("Kas teeme uue mängu? (jah/ei) ");
            String uusmäng = in.next();
            if (uusmäng.equals("ei")) {
                System.out.println("Järgmise korrani!");
                break;
            } else if (uusmäng.equals("jah")) {
                System.out.println("Alustame uut mängu!");
            }
        }
    }
}
