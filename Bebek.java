// extends 1 abstract class + implements interface
public class Bebek extends Hewan implements Berenang {
    public Bebek(String nama) {
        super(nama);
    }

    public void suara() {
        System.out.println(nama + " Kwek kwek!");
    }

    public void berenang() {
        System.out.println(nama + " berenang di kolam.");
    }
}
