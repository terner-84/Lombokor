
package cz.terner.lombokor.common;

import java.io.File;
import java.io.IOException;

public class TestConfig {
    public static void main(String[] args) throws IOException {
        ProgramConfig pc = new ProgramConfig();
        File file = pc.getFileConfig();
        pc.handleConfig(file);
        //System.out.println("file: " + file.getCanonicalPath());
    }
}
