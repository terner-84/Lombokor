/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.terner.lombokor.mailer;

import java.util.Properties;

/**
 *
 * @author hanus
 */
public class Mail {

    public static void main(String[] args) {
        String recipient = "";
        String sender = "";
        String host = "smtp.gmail.com";
        String port = "587";
        String password = "";

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        System.out.println(props);
    }

}
