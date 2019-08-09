/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.terner.lombokor.beans;

import lombok.Data;

/**
 *
 * @author hanus
 */
@Data
public class Item {
    private long id;
    private String it_name;
    private double it_price;
}
