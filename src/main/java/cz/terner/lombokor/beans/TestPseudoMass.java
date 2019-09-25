
package cz.terner.lombokor.beans;

import static cz.terner.lombokor.beans.TestBatis.getListPrns;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestPseudoMass {
    
    public static void main(String[] args) {
        fillMassPrnsTable();
    }
    
    private static void fillMassPrnsTable() {
        List<String> prns = getListPrns();
        Collections.shuffle(prns);
        int relevantMassPrnCount = 200 / 4;
        Set<String> relevantPrns = new TreeSet<>();
        List<MassPrs> massPrses = new ArrayList<>();
        Random rand = new Random();
        while (relevantPrns.size() < relevantMassPrnCount) {
            relevantPrns.add(prns.get(rand.nextInt(prns.size())));
        }
        relevantPrns.forEach(rp -> {
            MassPrs mp = new MassPrs();
            mp.setMass(ThreadLocalRandom.current().nextDouble(15000));
            mp.setPr(rp);
            massPrses.add(mp);
        });
        
        TestBatis.insert("fillMassPrs", massPrses);
    }
    
    public static void selectMassByPrns() {
        List<MassPlus> massPluses = null;
        MassPlusParams mpp = new MassPlusParams();
        List<String> massPrs = new ArrayList<>();
        massPrs.add("JJ7");
        massPrs.add("5ND");
        mpp.setPr("%JJ7%");
        mpp.setPrs(massPrs);
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            try (SqlSession session = sessionFactory.openSession()) {
                massPluses = session.selectList("pseudoMass", mpp);
                session.commit();
                session.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TestBatis.class.getName()).log(Level.SEVERE, null, ex);
        }
        massPluses.forEach(mp -> {
            System.out.println(mp);
        });
    }
    
}
