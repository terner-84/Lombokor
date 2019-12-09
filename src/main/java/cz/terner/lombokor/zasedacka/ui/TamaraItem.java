
package cz.terner.lombokor.zasedacka.ui;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TamaraItem {
    private int id;
    private String timeStart;
    private String timeEnd;
    private TamaraCellType occ;
    
    
    public static List<TamaraItem> genTamaraItems() {
        List<TamaraItem> tamaraItems = new ArrayList<>();
        int timeMin = 60 * 6;
        int timeMax = 60 * 17;
        for (int i = timeMin; i < timeMax; i += 15) {
            TamaraItem ti = new TamaraItem();
            ti.setId(i);
            ti.setTimeStart(parseIntMinuteToStringTime(i));
            ti.setTimeEnd(parseIntMinuteToStringTime(i + 15));
            ti.setOcc(TamaraCellType.FREE);
            tamaraItems.add(ti);
        }
        return tamaraItems;
    }
    
    private static String parseIntMinuteToStringTime(int time) {
        int h = time / 60;
        int m = time % 60;
        String out = h < 10 ? "0" + h : String.valueOf(h);
        out += m < 10 ? ":0" + m : ":" + m;
        return out;
    }
    
}
