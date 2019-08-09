
package cz.terner.lombokor.beans;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public static void main(String[] args) {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession session = sessionFactory.openSession()) {
                List<Item> items = genItems(2000000);
                AtomicInteger counter = new AtomicInteger();
                int chunkSize = 10000;
                Collection<List<Item>> result = items.stream()
                        .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize)).values();
                System.out.println("Počet items " + result.size());
                result.forEach(col -> {
                    session.insert("insert", col);
                });
                //session.insert("insert", items);
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
