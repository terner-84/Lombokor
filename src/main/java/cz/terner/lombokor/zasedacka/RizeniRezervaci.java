
package cz.terner.lombokor.zasedacka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.joda.time.DateTimeComparator;

public class RizeniRezervaci {
    
    public static final int MINIMALNI_CASOVY_USEK = 15;
    public static final int POCET_MINUT_V_HODINE = 60;
    public static final int MINIMALNI_POCATEK_REZERVACE = POCET_MINUT_V_HODINE * 6;
    public static final int MAXIMALNI_KONEC_REZERVACE = POCET_MINUT_V_HODINE * 17;
    
    public static List<VolneCasy> vratitVolneCasyProMistnostADatum(Map<Mistnost, List<Rezervace>> testovaciRezervace, Mistnost m) {
        List<Rezervace> vybranePodleMistnosti = testovaciRezervace.get(m);
        Map<String, List<Rezervace>> rezervacePodleDatumu = vybranePodleMistnosti.stream().collect(Collectors.groupingBy(c -> 
                String.format("%d-%d-%d", c.getDatumRezervace().get(Calendar.YEAR),c.getDatumRezervace().get(Calendar.MONTH)+1,c.getDatumRezervace().get(Calendar.DAY_OF_MONTH))));
        /*for (Map.Entry<String, List<Rezervace>> entry : rezervacePodleDatumu.entrySet()) {
            String key = entry.getKey();
            List<Rezervace> value = entry.getValue();
            System.out.println(key);
            value.forEach(System.out::println);
        }*/
        List<Rezervace> prvniVybraneRezervaceDne = rezervacePodleDatumu.get("2019-12-5");
        //Map<Integer, Integer> volneCasyAJejichTrvani = 
        int[] konce = prvniVybraneRezervaceDne.stream().mapToInt(rez -> rez.getCasRezervace() + rez.getRezervacniDoba()).toArray();
        int[] zacatky = prvniVybraneRezervaceDne.stream().mapToInt(rez -> rez.getCasRezervace()).toArray();
        System.out.println("ZACATKY " + Arrays.toString(zacatky));
        System.out.println("KONCE " + Arrays.toString(konce));
        
        int x = zacatky[0] - RizeniRezervaci.MINIMALNI_POCATEK_REZERVACE;
        System.out.println(x);
        List<VolneCasy> volneCasy = new ArrayList<>();
        for (int i = 0; i < zacatky.length; i++) {
            VolneCasy volnyCas = new VolneCasy();
            volnyCas.setStart(konce[i]);
            if ((i + 1) >= zacatky.length) {
                volnyCas.setEnd(RizeniRezervaci.MAXIMALNI_KONEC_REZERVACE);
            } else {
                volnyCas.setEnd(zacatky[i + 1]);
            }
            if (volnyCas.getStart() - volnyCas.getEnd() != 0) {
                volneCasy.add(volnyCas);
            }
        }
        //pořešit první
        if (zacatky[0] - RizeniRezervaci.MINIMALNI_POCATEK_REZERVACE > 0) {
            VolneCasy vcf = new VolneCasy();
            vcf.setStart(RizeniRezervaci.MINIMALNI_POCATEK_REZERVACE);
            vcf.setEnd(zacatky[0]);
            volneCasy.add(0, vcf);
        }
        volneCasy.forEach(vc -> {
            System.out.println(vc.getFreeTime() + " " + vc.getStart() + " - " + vc.getEnd());
        });
        return volneCasy;
    }
}
