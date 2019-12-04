
package cz.terner.lombokor.zasedacka;

import java.util.List;
import java.util.Map;

public class TestovaniRezervaci {
    public static void main(String[] args) {
        Map<Mistnost, List<Rezervace>> testovaciRezervace = GeneratorRezervaci.generujRezervace(15);
        //RezervaceStorage storage = new RezervaceStorage();
        RizeniRezervaci.vratitVolneCasyProMistnostADatum(testovaciRezervace, Mistnost.M1);
        //storage.save(testovaciRezervace);
        
        /*
        for (Map.Entry<Mistnost, List<Rezervace>> entry : testovaciRezervace.entrySet()) {
            Mistnost mistnost = entry.getKey();
            List<Rezervace> seznamRezervaci = entry.getValue();
            System.out.println("Mistnost " + mistnost);
            seznamRezervaci.forEach(System.out::println);
        }*/
    }
}
