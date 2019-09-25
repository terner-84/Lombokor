
package cz.terner.lombokor.beans;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.RandomStringUtils;

public class BatisUtils {
    public static Set<String> genPrns(int count) {
        Set<String> prns = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            prns.add(RandomStringUtils.random(3, true, true).toUpperCase());
        }
        prns.forEach(p -> {
            System.out.println(p);
        });
        return prns;
    }
    
    public static Timestamp randomDatetimeManufacture() {
        Calendar cal = Calendar.getInstance();
        Random rand = new Random();
        int odecetMasicu = rand.nextInt(36);
        cal.add(Calendar.MONTH, -odecetMasicu);
        cal.add(Calendar.HOUR, - (odecetMasicu + 3));
        return new Timestamp(cal.getTimeInMillis());
    }
    
    /*
    public static void main(String[] args) {
        System.out.println(randomDatetimeManufacture());
    }*/
}
