
package cz.terner.lombokor.zasedacka;

import com.esotericsoftware.yamlbeans.YamlWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RezervaceStorage {
    private YamlWriter yw;
    public void save(Object obj) {
        try {
            yw = new YamlWriter(new FileWriter("rezervace_test_file.yml"));
            yw.write(obj);
            yw.close();
        } catch (IOException ex) {
            Logger.getLogger(RezervaceStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
