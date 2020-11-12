
package cz.terner.lombokor.textreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ReadText {
    
    public static void main(String[] args) throws IOException {
        readBuffer();
    }
    
    private static void read() throws FileNotFoundException, IOException {
        File f = new File("text.txt");
        InputStream is = new FileInputStream(f);
        while (is.available() != 0) {
            System.out.println(is.read());
        }
        is.close();
    }
    
    private static void readBuffer() throws FileNotFoundException, IOException {
        File f = new File("text.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int counter;
        Map<Character, Integer> freq = new TreeMap<>();
        while (br.ready()) {
            char[] charArray = br.readLine().toCharArray();
            for (char c : charArray) {
                if (freq.containsKey(c)) {
                   counter = freq.get(c);
                   freq.put(c, ++counter);
                } else {
                    freq.put(c, 1);
                }
            }
        }
        br.close();
        fr.close();
        freq.forEach((key, value) -> {
            System.out.printf("%s >>> %d %n", key, value);
        });
    }
}
