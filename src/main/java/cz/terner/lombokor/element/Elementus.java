
package cz.terner.lombokor.element;

import java.util.Arrays;

public class Elementus {
    
    public static void main(String[] args) {
        int[] elements = new int[40];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = i + 1; 
        }
        //System.out.println(Arrays.toString(elements));
        
        for (int element : elements) {
            if ((element + 1) % 4 == 0) {
                System.out.println(element);
            }
        }
        
    }
    
}
