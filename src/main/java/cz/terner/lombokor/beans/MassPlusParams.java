
package cz.terner.lombokor.beans;

import java.util.List;
import lombok.Data;

@Data
public class MassPlusParams {
    private String pr;
    private String date;
    private List<String> prs;
    private int prsSize;
    
    public int getPrsSize() {
        int size = 0;
        if (prs != null) {
            size = prs.size();
        }
        return size;
    }
    
}
