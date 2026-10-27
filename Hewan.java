// Abstract class: tidak bisa di-instansiasi langsung (new Hewan() -> error)
public abstract class Hewan {
    protected String nama;

    public Hewan(String nama) {
        this.nama = nama;
    }

    public abstract void suara();

    public void tidur() {
        System.out.println(nama + " sedang tidur... zzz");
    }

    public String getNama() {
        return nama;
    }
}
