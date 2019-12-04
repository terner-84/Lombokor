
package cz.terner.lombokor.zasedacka;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestovaniRezervaci {
    public static void main(String[] args) {
        Map<Mistnost, List<Rezervace>> testovaciRezervace = GeneratorRezervaci.generujRezervace(15);
        //RezervaceStorage storage = new RezervaceStorage();
        List<VolneCasy> volneCasy1 = RizeniRezervaci.vratitVolneCasyProMistnostADatum(testovaciRezervace, Mistnost.M1);
        List<VolneCasy> volneCasy2 = RizeniRezervaci.vratitVolneCasyProMistnostADatum(testovaciRezervace, Mistnost.M2);
        List<VolneCasy> volneCasy3 = RizeniRezervaci.vratitVolneCasyProMistnostADatum(testovaciRezervace, Mistnost.M3);
        
        
        String zasOd = "14:30";
        String zasDo = "14:45";
        System.out.println("Kde mám volno od " + zasOd + " do " + zasDo);
        Optional<VolneCasy> v1 = volneCasy1.stream().filter(x -> x.checkFree(zasDo, zasDo)).findFirst();
        if (v1.isPresent()) {
            System.out.println("Volna mistnost " + Mistnost.M1 + " " + v1.get().getFreeTime());
        }
        Optional<VolneCasy> v2 = volneCasy2.stream().filter(x -> x.checkFree(zasDo, zasDo)).findFirst();
        if (v2.isPresent()) {
            System.out.println("Volna mistnost " + Mistnost.M2 + " " + v2.get().getFreeTime());
        }
        Optional<VolneCasy> v3 = volneCasy3.stream().filter(x -> x.checkFree(zasDo, zasDo)).findFirst();
        if (v3.isPresent()) {
            System.out.println("Volna mistnost " + Mistnost.M3 + " " + v3.get().getFreeTime());
        }
        
        
        
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
