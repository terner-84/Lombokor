
package cz.terner.lombokor.beans;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hanus
 */
public class Rundaba {
    public static void main(String[] args) {
        
        /*
        City city = new City();
        city.setArea(150);
        city.setCitizenCount(50000);
        city.setCityName("Mladá Boleslav");
        System.out.println(city);
        */
        
        /*
        try {
            YamlWriter yw = new YamlWriter(new FileWriter("cities.yml"));
            yw.write(city);
            yw.close();
        } catch (IOException ex) {
            Logger.getLogger(Rundaba.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
        
        try {
            YamlReader yr = new YamlReader(new FileReader("cities.yml"));
            City mlb = yr.read(City.class);
            System.out.println(mlb);
            yr.close();
        } catch (IOException ex) {
            Logger.getLogger(Rundaba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        City someOtherCity = new City();
        someOtherCity.setCityName("Prague");
        someOtherCity.setArea(500);
        System.out.println(someOtherCity.hashCode());
        
        City prague = new City();
        prague.setCityName("Prague");
        System.out.println(prague.hashCode());
        
    }
    
    
    
}
