public class Ikan extends Hewan implements Berenang {
    public Ikan(String nama) {
        super(nama);
    }

    public void suara() {
        System.out.println(nama + " diam saja (blub blub).");
    }

    public void berenang() {
        System.out.println(nama + " berenang di akuarium.");
    }
}
