
package cz.terner.lombokor.zasedacka;

import java.util.Calendar;
import lombok.Data;

@Data
public class Rezervace implements Comparable<Rezervace>{
    private String uzivatel;
    private Calendar datumRezervace;
    private int casRezervace;
    private int rezervacniDoba;

    @Override
    public int compareTo(Rezervace arg0) {
        return casRezervace - arg0.casRezervace;
    }
}
