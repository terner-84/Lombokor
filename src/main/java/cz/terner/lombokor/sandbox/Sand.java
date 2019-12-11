
package cz.terner.lombokor.sandbox;

import java.util.List;

public class Sand {
    public static void main(String[] args) {
        List<Item> items = Item.genList();
        Item.getArrayStrings(items);
    }
}
