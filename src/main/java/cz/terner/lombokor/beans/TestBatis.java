
package cz.terner.lombokor.beans;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestBatis {
    
    static int counter = 0;
    
    public static void main(String[] args) {
        //Set<String> prns = BatisUtils.genPrns(300);
        //insert(prns);
        List<String> prns = getListPrns();
        Collections.shuffle(prns);
        Collection<Set<String>> generatedPrnsSet = new ArrayList<>();
        List<String> collectedPrns = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            Set<String> x = new TreeSet<>();
            while (x.size() < 20) {
                x.add(prns.get(rnd.nextInt(prns.size())));
            }
            String y = x.stream().collect(Collectors.joining("+"));
            collectedPrns.add("+" + y);
            generatedPrnsSet.add(x);
            Collections.shuffle(prns);
        }
        //Collection<List<PrCatalogue>> outPrc = new ArrayList<>();
        generatedPrnsSet.stream().forEach(c -> {
            List<PrCatalogue> prCatalogueList = new ArrayList<>();
            
            counter++;
            c.forEach(prf -> {
                PrCatalogue prc = new PrCatalogue();
                prc.setModelNum(counter);
                prc.setPr(prf);
                prCatalogueList.add(prc);
                
                
            });
            //outPrc.add(gull);
            System.out.println(prCatalogueList);
            insert("catFill", prCatalogueList);
            
        });
        
        //insert mass
        List<Mass> massList = new ArrayList<>();
        for (int i = 0; i < collectedPrns.size(); i++) {
            Mass mass = new Mass();
            mass.setBackAxle(ThreadLocalRandom.current().nextDouble(1500));
            mass.setFrontAxle(ThreadLocalRandom.current().nextDouble(1500));
            mass.setDateMan(BatisUtils.randomDatetimeManufacture());
            mass.setMassId(i + 1);
            mass.setPr(collectedPrns.get(i));
            massList.add(mass);
        }
        insert("massFill", massList);
        
        System.out.println("BLACK HOLES");
        collectedPrns.forEach(bh -> {
            
        });
    }
    
    public static List<String> getListPrns() {
        List<String> prns = null;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession session = sessionFactory.openSession()) {
                prns = session.selectList("loadPrns");
                session.commit();
                session.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TestBatis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prns;
    }
    
    public static void insert(String id, Collection obj) {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession session = sessionFactory.openSession()) {
                /*
                List<Item> items = genItems(2000000);
                AtomicInteger counter = new AtomicInteger();
                int chunkSize = 10000;
                Collection<List<Item>> result = items.stream()
                        .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize)).values();
                System.out.println("Počet items " + result.size());
                result.forEach(col -> {
                    session.insert("insert", col);
                });
                */
                session.insert(id, obj);
                //List<Item> items = session.selectList("selectAll", 5);
                //items.forEach(i -> System.out.println(i));
                
                session.commit();
                session.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TestBatis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static List<Item> genItems(int limit) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            String name = RandomStringUtils.random(10, true, true);
            double price = ThreadLocalRandom.current().nextDouble(5, 100);
            Item item = new Item();
            item.setIt_name(name);
            item.setIt_price(price);
            items.add(item);
        }
        return items;
    }
}
