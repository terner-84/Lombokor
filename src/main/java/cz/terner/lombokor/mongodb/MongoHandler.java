
package cz.terner.lombokor.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import cz.terner.lombokor.bitset.BsHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;

/**
 *
 * @author hanus
 */
public class MongoHandler {
    private MongoClient mClient;
    private MongoDatabase db;
    private MongoCollection mColl;
    
    public MongoHandler() {
        mClient = new MongoClient("localhost", 27017);
        db = mClient.getDatabase("desiree");
       
    }
    
    public List<Document> getDocuments(String collection) {
        List<Document> docs = new ArrayList<>();
        mColl = db.getCollection("prns");
        FindIterable<Document> iterator = mColl.find();
        MongoCursor<Document> cursor = iterator.iterator();
        while (cursor.hasNext()) {
            docs.add(cursor.next());
        }
        return docs;
    }
    
    public void newDoc(String collection, int count) {
        db.createCollection(collection);
        MongoCollection mongoCollection = db.getCollection(collection);
        List<Document> docList = new ArrayList<>();
        Profiler.s();
        for (int i = 0; i < count; i++) {
            Document doc = new Document();
            doc.put("modelKey", RandomStringUtils.random(5, true, true));
            doc.put("modelYear", ThreadLocalRandom.current().nextInt(2018, 2022));
            doc.put("datetimeManufature", Utils.getDateBetween());
            doc.put("genFA", ThreadLocalRandom.current().nextInt(400000, 900000));
            doc.put("genBA", ThreadLocalRandom.current().nextInt(400000, 900000));
            doc.put("massFA", ThreadLocalRandom.current().nextInt(400000, 900000));
            doc.put("massBA", ThreadLocalRandom.current().nextInt(400000, 900000));
            doc.put("p", Utils.p());
            doc.put("vin", RandomStringUtils.random(25, true, true));
            docList.add(doc);
        }
        Profiler.takes("making documents");
        Profiler.s();
        mongoCollection.insertMany(docList);
        Profiler.takes("db inserting many");
    }
    
    @Deprecated
    public void createDocsAndFillWithRandom(String collection, int count) {
        db.createCollection(collection);
        MongoCollection colPrn = db.getCollection(collection);
        BsHandler bh = new BsHandler();
        Set<String> prns = bh.genPrns(100000);
        List<Document> docList = new ArrayList<>();
        prns.forEach(prn -> {
            Document docprn = new Document();
            docprn.put("prn", prn);
            docprn.put("weight", ThreadLocalRandom.current().nextDouble(20, 6000));
            //colPrn.insertOne(docprn);
            docList.add(docprn);
        });
        colPrn.insertMany(docList);
    }
    
}
