//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package repository;

import animal.Animal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AnimalFileRepository {
    private static final String FILE_NAME = "animals.txt";
//Sabit dosya adı.
//→ static + final → her yerde aynı, değişmez.

    public AnimalFileRepository() {
    }
    public static void saveAnimal(Animal animal) {
        //Metot Animal alıyor.
        //→ Dog, Cat, Bird hepsi buraya gelebilir.
        //➡ Bu polymorphism:
        //Dog da versen, Cat de versen Animal gibi davranır

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("animals.txt", true))) {
            //Dosya yazma işlemi.
            //→ true → dosyanın sonuna ekler.
            //→ try-with-resources → dosya otomatik kapanır.
            //➡ File processing + try-catch.

            String type = animal.getClass().getSimpleName().toUpperCase();
            //Hayvanın class adı alınır.
            //→ Dog / Cat / Bird otomatik bulunur.
            //➡ Polymorphism + reflection mantığı.

            bw.write(type + ";" + animal.getName() + ";" + animal.getOwner().getTc() + ";" + animal.getChip().getChipNumber());
            //Hayvan bilgileri dosyaya yazılır.
            //→ name → Animal’dan
            //→ owner → Person’dan
            //→ chip → Chip class’ından
            //➡ Nesneler arası ilişki net.

            bw.newLine();
            //Bir alt satıra geç.

        } catch (IOException var6) {
            System.out.println("Animal file write error.");
        }
        //Dosya hatası olursa program çökmez.
        //→ Mesaj basar.
        //➡ Exception handling.

    }

    public static List<String> readAllAnimals() {
        //Tüm hayvanları dosyadan okumak için.

        List<String> animals = new ArrayList();
//Okunan satırlar burada tutulur.

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("animals.txt"))) {
          //Dosya okuma başlar.
            while((line = br.readLine()) != null) {
                animals.add(line);
            }
            //Dosya satır satır okunur.
            //→ Listeye eklenir.

        } catch (IOException var6) {
            System.out.println("Animal file read error.");
        }
        //Dosya yoksa / bozuksa çökmez.

        return animals;
    }
    //Okunan veriler menüye gönderilir.
}
