# Abstract Class & Interface — Tugas PBO

Contoh program Java sederhana yang menunjukkan **abstract class**, **interface**, **inheritance**, dan **polymorphism**. Setiap class ditulis di file terpisah, sesuai prinsip OOP.

## Struktur File

```
.
├── Hewan.java      → abstract class (induk)
├── Berenang.java   → interface (kemampuan)
├── Kucing.java     → subclass dari Hewan
├── Anjing.java     → subclass dari Hewan
├── Bebek.java      → subclass dari Hewan + implement Berenang
├── Ikan.java       → subclass dari Hewan + implement Berenang
└── Main.java       → program utama (demo polymorphism)
```

---

## Konsep yang Dipakai

### 1. Abstract Class — `Hewan.java`

Abstract class adalah class yang **tidak bisa dibuat objeknya secara langsung**. Fungsinya sebagai kerangka/induk bagi class turunannya.

```java
public abstract class Hewan {
    protected String nama;
    ...
}

// Hewan h = new Hewan("X");   // ERROR: Hewan is abstract
```

Abstract class boleh berisi:
- atribut dan constructor (`nama`, `Hewan(String nama)`),
- method biasa yang sudah ada isinya (`tidur()`, `getNama()`),
- method abstract yang belum ada isinya (`suara()`).

### 2. Abstract Method — `suara()`

Method abstract hanya punya **deklarasi tanpa isi** (tanpa `{ }`). Setiap subclass yang konkret **wajib** mengimplementasikannya.

```java
public abstract void suara();
```

Alasannya: semua hewan pasti bersuara, tapi suaranya beda-beda. Jadi `Hewan` hanya menetapkan "harus ada `suara()`", dan cara bersuaranya ditentukan masing-masing subclass (`Kucing` → "Meong!", `Anjing` → "Guk guk!").

### 3. Interface — `Berenang.java`

Interface adalah **kontrak** yang berisi daftar method yang harus dimiliki class yang meng-implement-nya. Isi method sengaja kosong karena cara kerjanya ditentukan oleh class yang meng-implement.

```java
public interface Berenang {
    void berenang();   // otomatis public abstract
}
```

Interface dipakai untuk **kemampuan** yang bisa dimiliki class mana pun, tanpa harus satu keluarga. Di sini hanya `Bebek` dan `Ikan` yang bisa berenang, sedangkan `Kucing` dan `Anjing` tidak.

### 4. Inheritance (Pewarisan)

Inheritance memungkinkan subclass **mewarisi** atribut dan method dari superclass memakai kata kunci `extends`.

```java
public class Kucing extends Hewan {
    public Kucing(String nama) {
        super(nama);   // memanggil constructor Hewan
    }
}
```

- `extends` → mewarisi class (hanya boleh **satu** superclass).
- `super(nama)` → memanggil constructor milik `Hewan` untuk mengisi `nama`.
- `protected` pada `nama` → bisa diakses langsung oleh subclass (`Kucing`, `Anjing`, dst.).
- Semua subclass otomatis punya `tidur()` dan `getNama()` tanpa menulis ulang.

### 5. Implements Interface — `Bebek` dan `Ikan`

Sebuah class boleh `extends` satu class **sekaligus** `implements` satu atau lebih interface.

```java
public class Bebek extends Hewan implements Berenang {
    public void suara()    { ... }   // wajib, dari Hewan (abstract method)
    public void berenang() { ... }   // wajib, dari Berenang (interface)
}
```

Perbedaan cara berenang tiap class:
- `Bebek` → "berenang di kolam"
- `Ikan` → "berenang di akuarium"

### 6. Polymorphism (Banyak Bentuk)

Polymorphism berarti **satu pemanggilan method, hasilnya bisa berbeda tergantung objek aslinya**. Java menentukan method mana yang dijalankan saat program berjalan (*dynamic binding*), bukan saat penulisan kode.

**a. Lewat abstract class** — satu array bertipe `Hewan` bisa menampung berbagai subclass:

```java
Hewan[] daftarHewan = {
    new Kucing("Tom"), new Anjing("Bruno"),
    new Bebek("Donal"), new Ikan("Nemo")
};

for (Hewan h : daftarHewan) {
    h.suara();   // Kucing → Meong, Anjing → Guk guk, dst.
}
```

Tipe variabelnya `Hewan`, tapi objek aslinya berbeda-beda, sehingga `suara()` yang dijalankan mengikuti objek aslinya.

**b. Lewat interface** — variabel bertipe `Berenang` bisa menunjuk objek class apa pun yang meng-implement-nya:

```java
Berenang[] perenang = { new Bebek("Donal"), new Ikan("Nemo") };

for (Berenang b : perenang) {
    b.berenang();   // Bebek → di kolam, Ikan → di akuarium
}
```

Pemanggil tidak perlu tahu itu bebek atau ikan, cukup tahu bahwa objeknya bisa `berenang()`.

### 7. `instanceof` dan Casting

Tidak semua `Hewan` bisa berenang. Sebelum memanggil `berenang()`, kita cek dulu dengan `instanceof`, lalu ubah tipenya (*casting*) ke `Berenang`:

```java
if (h instanceof Berenang) {
    ((Berenang) h).berenang();
} else {
    System.out.println(h.getNama() + " tidak bisa berenang.");
}
```

---

## Abstract Class vs Interface

| Aspek | Abstract Class | Interface |
|---|---|---|
| Kata kunci | `abstract class` + `extends` | `interface` + `implements` |
| Jumlah yang bisa diwarisi | Hanya 1 | Boleh lebih dari 1 |
| Atribut & constructor | Boleh | Tidak ada constructor (atribut hanya konstanta) |
| Method | Boleh abstract dan boleh sudah ada isinya | Pada dasarnya abstract (isi hanya lewat `default`/`static`) |
| Dipakai untuk | Hubungan "adalah" (*is-a*): Kucing **adalah** Hewan | Kemampuan "bisa" (*can-do*): Bebek **bisa** Berenang |
| Contoh di program | `Hewan` | `Berenang` |

---

## Cara Menjalankan

Pastikan JDK sudah terpasang, lalu dari folder yang berisi semua file `.java`:

```bash
javac *.java
java Main
```

## Contoh Output

```
=== Polymorphism lewat abstract class ===
Tom Meong!
Tom sedang tidur... zzz
Bruno Guk guk!
Bruno sedang tidur... zzz
Donal Kwek kwek!
Donal sedang tidur... zzz
Nemo diam saja (blub blub).
Nemo sedang tidur... zzz

=== Polymorphism lewat interface ===
Donal berenang di kolam.
Nemo berenang di akuarium.

=== Cek kemampuan dengan instanceof ===
Tom tidak bisa berenang.
Bruno tidak bisa berenang.
Donal berenang di kolam.
Nemo berenang di akuarium.
```