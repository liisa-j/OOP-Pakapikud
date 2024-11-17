package oop;

public class Edetabel {
    private int minuVõidud;
    private int vastaseVõidud;
    private int mängeMängitud;
    private int minuKäigud;
    private int vastaseKäigud;

    public Edetabel() {
        this.minuVõidud = 0;
        this.vastaseVõidud = 0;
        this.mängeMängitud = 0;
        this.minuKäigud = 0;
        this.vastaseKäigud = 0;
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
    public void addVastaseKäigud() {
        this.vastaseKäigud++;
    }
    public void statistika() {
        System.out.println("Kõikide mängude arv " +mängeMängitud + "." );
        System.out.println("Minu võidud " + minuVõidud + " ning minu käigud " + minuKäigud + ".");
        System.out.println("Vastase võidud " + vastaseVõidud + " ning tema käigud " + vastaseKäigud + ".");

    }

    public int getMinuVõidud() {
        return minuVõidud;
    }

    public int getVastaseVõidud() {
        return vastaseVõidud;
    }

    public int getMängeMängitud() {
        return mängeMängitud;
    }

    public int getMinuKäigud() {
        return minuKäigud;
    }

    public int getVastaseKäigud() {
        return vastaseKäigud;
    }
}
