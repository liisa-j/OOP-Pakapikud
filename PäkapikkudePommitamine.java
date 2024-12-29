package oop;

import java.io.IOException;
import java.util.Scanner;

public class PäkapikkudePommitamine {

    private static void tutvustusTekst() {
        System.out.println();
        System.out.println("               Päkapikkude pommitamise mängujuhend:");
        System.out.println("1. Mängu alustades tuleb valida mänguvälja suurus (alates 3 kuni 10). ");
        System.out.println("2. Järgmisena kuvatakse soovitud mänguväli (M-mets ehk pommitamata ala).");
        System.out.println("3. Samuti annab mäng teada, palju päkapikke on metsas peidus mängijatel. ");
        System.out.println("4. Nüüd tuleb otsustada, millist asukohta soovitakse pommitada.");
        System.out.println("4. Pihtasaamisel kuvatakse mänguväljal X, möödapanekul kuvatakse O.");
        System.out.println("5. Võidab see mängija, kes saab vastase päkapikkudele esimesena pihta.");
        System.out.println("               Nüüd kui kõik on selge, saab alustada mängimist!");
    }


    public static void main(String[] args) throws IOException {
        tutvustusTekst();
        char mets = 'M';
        Edetabel info = new Edetabel();
        String failinimi = "päkapikud.txt";

        Scanner in = new Scanner(System.in);

        try{
            info.loeEdetabelist(failinimi);
        }catch (SisendiErind e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Mida sooviksid teha?");
            System.out.println("1 - vaadata edetabelit, 2 - alustada uut mängu, 3 - kustutada edetabeli, 4 - lahkuda mängust");
            int valitud = in.nextInt();

            if (valitud == 2) {
                System.out.println("Sisesta mänguvälja pikkus: ");
                int mänguväljapikkus = in.nextInt();
                while (mänguväljapikkus < 3 || mänguväljapikkus > 10) {
                    System.out.println("Proovi uuesti, mänguvälja vahemik on 3-10 ruutu");
                    mänguväljapikkus = in.nextInt();
                }
                // loome mänguväljad ühele mängijale
                mänguVäli minu = new mänguVäli();
                minu.looMänguväli(mänguväljapikkus, mets);
                // minu.prindiMänguväli();
                System.out.println();


                System.out.println("Kas soovid valida ise päkapikkude asukohti või panna paika juhuslikult: (1/2)");
                int minuvalik = 0;
                while (minuvalik != 1 && minuvalik != 2) {
                    if (in.hasNextInt()) {
                        minuvalik = in.nextInt();
                        if (minuvalik == 1) {
                            minu.arvutaPäkapikud();
                            minu.valinPäkapikud();
                            System.out.println("Selles mängus osaleb päkapikke: " + minu.getPäkapikud());
                        } else if (minuvalik == 2) {
                            minu.arvutaPäkapikud();
                            minu.lisapikud();
                            System.out.println("Selles mängus osaleb kummalgi mängijal päkapikke: " + minu.getPäkapikud());
                        } else {
                            System.out.println("Proovi uuesti: 1 - valin ise päkapikud, 2 - päkapikkude juhuslikult paigutus");
                        }
                    } else {
                        System.out.println("Proovi sisestada number: 1 - valin ise päkapikud, 2 - päkapikkude juhuslikult paigutus");
                        in.next();
                    }
                }


                // loome mänguvälja teisele (arvutile):
                VastaseMänguVäli sinu = new VastaseMänguVäli();
                sinu.looMänguväli(mänguväljapikkus, mets);
                //sinu.prindiMänguväli();
                System.out.println();
                sinu.arvutaPäkapikud();
                sinu.lisapikud();

                // Siin teeme esialgse salvestuse mänguseisust (et vältida viga, mis tekib kui mängija üritab salvestamata mängu taastada)
                minu.salvestus();
                sinu.salvestus();

                do {

                    // Siin pakutakse iga käigu alguses võimalust mänguseisu salvestada või salvestust taastada
                    System.out.println("Mida soovid teha?" +  "\n" + "s - salvesta mänguseis" + "\n" + "t - taasata mängu salvestatud seis" + "\n" + "ükskõik, mis täht/number - jätka mängu!");
                    String salvestus = in.next();


                    // Mänguseisu salvestamine
                    if (salvestus.equals("s")) {
                        System.out.println("Mänguseis on salvestatud!");
                        minu.salvestus();
                        sinu.salvestus();
                    }

                    // Salvestatud mänguseisu taastamine
                    else if(salvestus.equals("t")){
                        System.out.println("Taastame mängu salvestuspunkti...");
                        minu.taastamine();
                        sinu.taastamine();
                    }

                    else {

                        System.out.println("Vastasel on siin peidus päkapikke: " + sinu.getPäkapikud());
                        sinu.prindiMänguväli();
                        info.addMinuKäigud();
                        sinu.pommita();

                        System.out.println();
                        System.out.println("Minul on alles päkapikke: " + minu.getPäkapikud());
                        info.addVastaseKäigud();
                        minu.pommita();
                        minu.prindiMänguväli();
                    }

                } while ((minu.getPäkapikud() > 0) && (sinu.getPäkapikud() > 0));


                System.out.println("Mäng sai läbi!");
                if (minu.getPäkapikud() == 0) {
                    System.out.println("Kahjuks kaotasid mängu! Parem õnn järgmisel korral!");
                    info.addVastaseVõidud();
                    info.addMängeMängitud(); //LISATUD
                } else {
                    System.out.println("Juhuuu, võitsid!");
                    info.addMinuVõidud();
                    info.addMängeMängitud(); //LISATUD
                }
            }else if (valitud == 1) {
                info.statistika();
            }else if (valitud == 3) {
                info.tagasiNulli();
            }else if (valitud == 4) {
                try {
                    info.salvestaEdetabel(failinimi);
                } catch (SisendiErind e) {
                    System.err.println("Viga faili salvestusel" + e.getMessage());
                }
                break;
            }else {
                System.out.println("Ebasobiv sisend!");
            }
        } in.close();
    }
}

