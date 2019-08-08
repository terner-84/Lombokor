
package cz.terner.lombokor.mongodb;

import cz.terner.lombokor.bitset.BsHandler;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import org.bson.Document;

/**
 *
 * @author hanus
 */
public class TestMongo {
    
    private static double absus = 0;
    
    public static void main(String[] args) {
        MongoHandler mh = new MongoHandler();
        mh.newDoc("ctirad", 500000);
    }
    
    public static void handlePrns(String[] args) {
        MongoHandler mh = new MongoHandler();
        List<Document> documents = mh.getDocuments("prns");
        System.out.println(documents.size());
        
        Map<Integer, Document> map = new TreeMap<>();
        for (int i = 0; i < 50; i++) {
            map.put(i, documents.get(ThreadLocalRandom.current().nextInt(documents.size())));
        }
        
        
        
        List<ArrayList<Integer>> heets = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            ArrayList<Integer> prnsIndexes;
            Set<Integer> nah = new TreeSet<>();
            for (int j = 0; j < 10; j++) {
                nah.add(ThreadLocalRandom.current().nextInt(50));
            }
            prnsIndexes = new ArrayList<>(nah);
            heets.add(prnsIndexes);
        }
                
        BsHandler bh = new BsHandler();
        ArrayList<Integer> dite = heets.get(0);
        heets.remove(0);
        
        long start = System.currentTimeMillis();
        
        List<Double> absVals = new ArrayList<>();
        heets.forEach(heet -> {
            if (dite.size() == heet.size()) {
                BitSet bs = bh.compareUs(dite, heet);
                bs.stream().forEach(b -> {
                    Document doc = map.get(b);
                    absus += Math.abs(doc.getDouble("weight"));
                });
                if (absus > 20000) {
                    absVals.add(absus);
                }
                absus = 0;
            }
        });
        System.out.println("Compairing BitSets takes: " + (System.currentTimeMillis() - start) + " secs.");
        
        System.out.println(absVals.size());
    }
}
