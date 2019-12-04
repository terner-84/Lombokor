
package cz.terner.lombokor.zasedacka;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneratorRezervaci {
    
    private static final Random RAND = new Random();
    private static final String[] UZIVATELE = {"Harry", "James", "Bobby", 
        "Krusty", "Maggie", "Homer", "Bart", "Marge", "Dr. Dlaha", "Mr. Burns", "Líza"};
    
    public static Map<Mistnost, List<Rezervace>> generujRezervace(int pocetRezervaci) {
        Map<Mistnost, List<Rezervace>> data = new HashMap<>();
        int posledniKonec = RizeniRezervaci.MINIMALNI_POCATEK_REZERVACE;
        for (Mistnost m : Mistnost.values()) {
            List<Rezervace> seznamRezervaci = new ArrayList<>();
            for (int i = 0; i < pocetRezervaci; i++) {
                Rezervace rezervace = new Rezervace();
                rezervace.setUzivatel(vratitNahodnehoUzivatele());
                int nahodnyCasRezervace = vratitZacatek(posledniKonec);
                rezervace.setCasRezervace(nahodnyCasRezervace);
                int nahodnaRezervacniDoba = vratitNahodnouRezervacniDobu();
                posledniKonec = nahodnyCasRezervace + nahodnaRezervacniDoba;
                rezervace.setRezervacniDoba(nahodnaRezervacniDoba);
                rezervace.setDatumRezervace(vratitNahodnyDatum());
                if (posledniKonec > RizeniRezervaci.MAXIMALNI_KONEC_REZERVACE) {
                    break;
                } else {
                    seznamRezervaci.add(rezervace);
                }
            }
            data.put(m, seznamRezervaci);
            posledniKonec = RizeniRezervaci.MINIMALNI_POCATEK_REZERVACE;
        }
        return data;
    }
    
    private static Calendar vratitNahodnyDatum() {
        boolean nahoda = true;
        Calendar cal = Calendar.getInstance();
        if (nahoda) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        return cal;
    }
    
    private static int vratitZacatek(int posledniKonec) {
        boolean nahoda = RAND.nextBoolean();
        int nahodnyZacatek;
        if (nahoda) {
            nahodnyZacatek = posledniKonec + RizeniRezervaci.MINIMALNI_CASOVY_USEK * 3;
        } else {
            nahodnyZacatek = posledniKonec + RizeniRezervaci.MINIMALNI_CASOVY_USEK * 6;
        }
        return nahodnyZacatek;
    }
    
    private static int vratitNahodnouRezervacniDobu() {
        boolean nahoda = RAND.nextBoolean();
        int nahodnaRezervacniDoba;
        if (nahoda) {
            nahodnaRezervacniDoba = RizeniRezervaci.MINIMALNI_CASOVY_USEK * 2;
        } else {
            nahodnaRezervacniDoba = RizeniRezervaci.MINIMALNI_CASOVY_USEK * 3;
        }
        return nahodnaRezervacniDoba;
    }
    
    private static String vratitNahodnehoUzivatele() {
        int nahodnyIndex = RAND.nextInt(UZIVATELE.length);
        return UZIVATELE[nahodnyIndex];
    }
}
