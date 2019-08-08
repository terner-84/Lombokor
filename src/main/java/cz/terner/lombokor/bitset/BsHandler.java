
package cz.terner.lombokor.bitset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author hanus
 */
public class BsHandler {
    private List<Integer> items;
    private BitSet current;
    private BitSet comparative;
    
    public BsHandler(/*Integer[] items*/) {
        //this.items = Arrays.asList(items);
        this.current = new BitSet();
        this.comparative = new BitSet();
    }
    
    public BitSet compareUs(ArrayList<Integer> curr, ArrayList<Integer> comp) {
        curr.forEach(ca -> {
            this.current.set(ca);
        });
        comp.forEach(ca -> {
            this.comparative.set(ca);
        });
        current.and(comparative);
        return current;
    }
    
    public BitSet compareIntegers(ArrayList<Integer> curr, ArrayList<Integer> comp) {
        BitSet out = new BitSet();
        Set<Integer> cur = new HashSet<>(curr);
        Set<Integer> com = new HashSet<>(comp);
        cur.removeAll(com);
        cur.forEach(c -> {
            out.set(c);
        });
        return out;
    }
    
    public Set<String> genPrns(int count) throws IllegalArgumentException {
        if (count < 1) {
            throw new IllegalArgumentException("Your count number < 1, has to be > 0 !!!");
        }
        Set<String> gp = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            gp.add(RandomStringUtils.random(3, true, true).toUpperCase());
        }
        return gp;
    }
    
}
