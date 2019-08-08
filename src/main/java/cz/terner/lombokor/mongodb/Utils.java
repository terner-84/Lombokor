
package cz.terner.lombokor.mongodb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author hanus
 */
public class Utils {
    
    private static final long TODAY = 1563321600000L;
    private static final long STARTDAY = 1496275200000L;
    
    public static String getDateBetween() {
        long longRandomDate = ThreadLocalRandom.current().nextLong(STARTDAY, TODAY);
        Date randomDate = new Date(longRandomDate);
        SimpleDateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return stringDateFormat.toString();
    }
    
    public static String p() {
        int count = ThreadLocalRandom.current().nextInt(140, 193);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("+");
            sb.append(RandomStringUtils.random(3, true, true));
        }
        return sb.toString();
    }
}
