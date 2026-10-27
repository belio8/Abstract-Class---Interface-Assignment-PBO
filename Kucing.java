// Inheritance: Kucing mewarisi Hewan
public class Kucing extends Hewan {
    public Kucing(String nama) {
        super(nama);
    }

    public void suara() {
        System.out.println(nama + " Meong!");
    }
}
