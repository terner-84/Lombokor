
package cz.terner.lombokor.zasedacka;

import lombok.Data;

@Data
public class VolneCasy {
    private int start;
    private int end;
    
    public String getFreeTime() {
        return String.format("Free from %s to %s", parseIntMinuteToStringTime(start), parseIntMinuteToStringTime(end));
    }
    
    public boolean checkFree(String pocatek, String konec) {
        int p = parseStringTimeToInt(pocatek);
        int k = parseStringTimeToInt(konec);
        boolean isFree = false;
        if (p >= start && k <= end) {
            isFree = true;
        }
        return isFree;
    }
    
    private String parseIntMinuteToStringTime(int time) {
        int h = time / 60;
        int m = time % 60;
        String out = h < 10 ? "0" + h : String.valueOf(h);
        out += m < 10 ? ":0" + m : ":" + m;
        return out;
    }
    
    private int parseStringTimeToInt(String time) {
        int h = Integer.parseInt(time.substring(0, 2)) * 60;
        int m = Integer.parseInt(time.substring(3));
        return h + m;
    }
}
