
package cz.terner.lombokor.triplets;

import java.util.Set;
import lombok.Data;

@Data
public class NeuBean {
    private int id;
    private Set<String> prGroup, famGroup;
    private String model;
    private double total, front, back;
}
