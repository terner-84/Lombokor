
package cz.terner.lombokor.zasedacka;

import lombok.Data;

@Data
public class VolneCasy {
    private int start;
    private int end;
    
    public String getFreeTime() {
        return String.format("Free from %s to %s", parseIntMinuteToStringTime(start), parseIntMinuteToStringTime(end));
    }
    
    private String parseIntMinuteToStringTime(int time) {
        int h = time / 60;
        int m = time % 60;
        String out = h < 10 ? "0" + h : String.valueOf(h);
        out += m < 10 ? ":0" + m : ":" + m;
        return out;
    }
}
