
package cz.terner.lombokor.beans;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class MassPlus {
    private int massId;
    private Timestamp dateMan;
    private String pr;
    private double frontAxle;
    private double backAxle;
    private double totalMass;
}
