public class Main {
    public static void main(String[] args) {
        
        Hewan[] daftarHewan = {
            new Kucing("Tom"),
            new Anjing("Bruno"),
            new Bebek("Donal"),
            new Ikan("Nemo")
        };

        System.out.println("=== Polymorphism lewat abstract class ===");
        for (Hewan h : daftarHewan) {
            h.suara();
            h.tidur();
        }

        System.out.println("\n=== Polymorphism lewat interface ===");
        Berenang[] perenang = {
            new Bebek("Donal"),
            new Ikan("Nemo")
        };
        for (Berenang b : perenang) {
            b.berenang();
        }

        System.out.println("\n=== Cek kemampuan dengan instanceof ===");
        for (Hewan h : daftarHewan) {
            if (h instanceof Berenang) {
                ((Berenang) h).berenang();
            } else {
                System.out.println(h.getNama() + " tidak bisa berenang.");
            }
        }
    }
}
