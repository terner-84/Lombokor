
package cz.terner.lombokor.bitset;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hanus
 */
public class BsTester {
    public static void main(String[] args) {
        
        Map<Integer, String> mapa = new HashMap<>();
        mapa.put(3, "alfa");
        mapa.put(8, "bingo");
        
        BsHandler bh = new BsHandler();
        
        Set<String> rndPrns = null;
        
        try {
            rndPrns = bh.genPrns(500);
        } catch (IllegalArgumentException e) {
            System.err.print(e.getMessage());
        }
        
        System.out.println(rndPrns);
        
        ArrayList<Integer> curr = new ArrayList<>();
        ArrayList<Integer> comp = new ArrayList<>();
        
        curr.add(8);
        curr.add(16);
        curr.add(3);
        
        comp.add(5);
        comp.add(3);
        comp.add(7);
        comp.add(8);
        
        List<Integer> bla = new ArrayList<>();
        
        BitSet result = bh.compareUs(curr, comp);
        result.stream().forEach(rb -> {
            System.out.println(mapa.get(rb));
            bla.add(rb);
        });
    }
}
