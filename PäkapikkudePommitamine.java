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

        while (true) {
            System.out.println("Sisesta mänguvälja pikkus: ");
            Scanner in = new Scanner(System.in);
            int mänguväljapikkus = in.nextInt();
            while (mänguväljapikkus < 3 || mänguväljapikkus > 10) {
                    System.out.println("Proovi uuesti, mänguvälja vahemik on 3-10 ruutu");
                    System.out.println("Sisesta mänguvälja pikkus: ");
                    mänguväljapikkus = in.nextInt();
            }

            // loome mänguväljad ühele mängijale
            mänguVäli minu = new mänguVäli();
            minu.looMänguväli(mänguväljapikkus, mets);
            minu.prindiMänguväli();
            System.out.println();
    
            minu.arvutaPäkapikud();
            minu.lisapikud();
            System.out.println("Selles mängus osaleb päkapikke: " + minu.getPäkapikud());
    
    
            // loome mänguvälja teisele (arvutile):
            VastaseMänguVäli sinu = new VastaseMänguVäli();
            sinu.setMänguväljapikkus(mänguväljapikkus);
            sinu.looMänguväli(mänguväljapikkus, mets);
            //sinu.prindiMänguväli();
            System.out.println();
    
            sinu.arvutaPäkapikud();
            sinu.lisapikud();
            System.out.println("Selles mängus osaleb arvutil päkapikke: " + sinu.getPäkapikud());

            info.addMängeMängitud();
    
    
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
            } else if(uusmäng.equals("jah")) {
                System.out.println("Alustame uut mängu!");
            }
        }
    }
}        
