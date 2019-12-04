
package cz.terner.lombokor.loggor;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.layout.PatternLayout;



public class TestLog {
    /*static { System.setProperty("logPath", "C:/logfiles"); }
    static {
        Cobu c = new Cobu();
        Configurator.initialize(c.x().build());
        try {
            c.x().writeXmlConfiguration(System.out);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TestLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOGGER = LogManager.getLogger(TestLog.class.getName());*/
    
    
    public static void main(String[] args) {
        Set<String> alfa = new TreeSet<>();
        Set<String> beta = new TreeSet<>();
        
        alfa.add("A01");
        alfa.add("A02");
        alfa.add("A03");
        
        Set<String> gamma = new TreeSet<>(alfa);
        
        beta.add("A02");
        beta.add("A03");
        beta.add("B02");
        gamma.addAll(beta);
        
        
        alfa.retainAll(beta);
        gamma.removeAll(alfa);
        System.out.println(alfa);
        System.out.println(gamma);
    }
}
