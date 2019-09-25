package cz.terner.lombokor.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfTest {
    
    static int counter = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        /*date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.SUNDAY
        );
        date.set(Calendar.HOUR, 7);
        date.set(Calendar.MINUTE, 30);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);*/
        // Schedule to run every Sunday in midnight
        System.out.println("Timer start");
        timer.schedule(
                new SampleTask(timer),
                3000,
                1000 * 5
            ); 
        
    }

    private static void readPdf() {
        PDDocument document;
        try {
            document = PDDocument.load(new File("test.pdf"));
            if (!document.isEncrypted()) {

                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                String[] s = text.split("\\R");
                String[] ss = s[1].split("\\t");
                System.out.println(new ArrayList<>(Arrays.asList(ss)));
            }
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(PdfTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class SampleTask extends TimerTask {

        private Timer timer;
        
        public SampleTask(Timer timer) {
            this.timer = timer;
        }
        
        @Override
        public void run() {
            if (counter > 6) {
                timer.cancel();
            }
            readPdf();
            counter++;
        }

    }

}
