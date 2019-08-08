
package cz.terner.lombokor.mongodb;

/**
 *
 * @author hanus
 */
public class Profiler {
    
    private static long start;
    
    public static void s() {
        start = System.currentTimeMillis();
    }
    
    public static void takes(String operation) {
        long timeTaken = System.currentTimeMillis() - start;
        System.out.println("Operation " + operation + " takes: " + timeTaken + " millis.");
    }
}
