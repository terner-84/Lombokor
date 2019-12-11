
package cz.terner.lombokor.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

@Data
public class Item {
    private String a;
    private int x;
    
    public static List<Item> genList() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setA(RandomStringUtils.random(5, true, true));
            item.setX(RandomUtils.nextInt(5418));
            items.add(item);
        }
        return items;
    }
    
    public static String[] getArrayStrings(List<Item> items) {
        String[] arr = new String[items.size()];
        Integer[] iarr = new Integer[items.size()];
        List<String> out = items.stream().map(m -> m.getA()).collect(Collectors.toList());
        List<Integer> ccccc = items.stream().map(m -> m.getX()).collect(Collectors.toList());
        iarr = ccccc.toArray(iarr);
        arr = out.toArray(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(iarr));
        for (Integer integer : iarr) {
            System.out.println(Integer.toBinaryString(integer));
            byte b = (byte) (integer >>> 1 & 0xff);
            System.out.printf("0x%32X %n", b);
        }
        System.out.println(Integer.toBinaryString(-Integer.MAX_VALUE).length());
        System.out.println(Double.doubleToRawLongBits(Double.MAX_VALUE));
        return arr;
    }
    
}
