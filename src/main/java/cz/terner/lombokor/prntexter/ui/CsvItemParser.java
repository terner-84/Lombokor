package cz.terner.lombokor.prntexter.ui;

import cz.terner.lombokor.prntexter.VssItem;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvItemParser {

    private final String[] HEADERS = {"partNumber", "vssId", "prns"};
    
    private List<VssItem> vssItems;

    public String readFile(String f) throws FileNotFoundException, IOException {
        Reader in = new FileReader(f);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .parse(in);
        vssItems = new ArrayList<>();
        for (CSVRecord record : records) {
            String partNumber = record.get("partNumber");
            String vssId = record.get("vssId");
            String prns = record.get("prns");
            VssItem vi = new VssItem();
            vi.setPartNumber(partNumber);
            vi.setPrns(prns);
            vi.setVssId(vssId);
            vssItems.add(vi);
        }
        StringBuilder sb = new StringBuilder();
        vssItems.forEach(item -> {
            sb.append(item);
            sb.append("\n");
        });
        return sb.toString();
    }
    
    public String examinePrns(String prn) {
        Pattern pattern = Pattern.compile("[^/]{1}[" + prn + "]{3}[^/]{1}");
        vssItems.forEach(item -> {
            Matcher matcher = pattern.matcher(item.getPrns());
            if (matcher.find()) {
                System.out.println(item.getPrns());
            }
        });
        return "null";
    }
    
}
