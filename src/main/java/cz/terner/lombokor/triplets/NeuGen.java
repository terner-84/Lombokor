
package cz.terner.lombokor.triplets;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class NeuGen {
    private final int MAX_COUNT_4 = 1;
    private final int MAX_COUNT_3 = 2;
    private final int MAX_COUNT_2 = 4;
    private final int MAX_COUNT_1 = 20;
    private final String[] models = {"AA", "BB", "CC", "DD"};
    private final List<String> modelsList = new ArrayList<>(Arrays.asList(models));
    private Queue<String> mbtQueue;
    private Random rand = new Random();
    private Set<String> relFams, relPrns;
    private Set<String> usedPrn, usedFams;
    private StringBuilder sb;
    private Map<String, Set<String>> mbt = new TreeMap<>();
    private List<Set<String>> neu;
    
    
    private char getRandomAlfa() {
        int x = rand.nextInt(26) + 65;
        return (char) x;
    }
    
    private char getRandomNumeric() {
        int x = rand.nextInt(10) + 48;
        return (char) x;
    }
    
    public String getRandomFam() {
        sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(getRandomAlfa());
        }
        return sb.toString();
    }
    
    private String getRandomPrn() {
        sb = new StringBuilder();
        boolean letter = false;
        for (int i = 0; i < 3; i++) {
            letter = rand.nextBoolean();
            if (letter) {
                sb.append(getRandomAlfa());
            } else {
                sb.append(getRandomNumeric());
            }
        }
        return sb.toString();
    }
    
    public void mapPrns() {
        genFams(150);
        genPrns(500);
        usedFams = new HashSet<>();
        usedPrn = new HashSet<>();
        Queue<String> fams = new ArrayDeque<>(relFams);
        Queue<String> prns = new ArrayDeque<>(relPrns);
        int maxPrns = 1;
        while (fams.size() > 0 && prns.size() > 4) {
            maxPrns = rand.nextInt(4) + 1;
            Set<String> localPrnSet = new HashSet<>();
            for (int i = 0; i < maxPrns; i++) {
                localPrnSet.add(prns.poll());
            }
            mbt.put(fams.poll(), localPrnSet);
        }
        System.out.println("Neobsazených rodin: " + fams.size());
        System.out.println(mbt);
        createNeu();
        System.out.println(neu);
        for (int i = 0; i < 10; i++) {
            createCarGroups(i);
        }
        
    }
    
    public Set<String> genFams(int count) {
        relFams = new TreeSet<>();
        while (relFams.size() < count) {
            relFams.add(getRandomFam());
        }
        return relFams;
    }
    
    private Set<String> genPrns(int count) {
        relPrns = new HashSet<>();
        while (relPrns.size() < count) {
            relPrns.add(getRandomPrn());
        }
        return relPrns;
    }
    
    private void createNeu() {
        neu = new ArrayList<>();
        List<String> shuffleList;
        Queue<String> fx;
        Set<String> famGroup;
        shuffleList = new ArrayList<>(mbt.keySet());
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_4; i++) {
            famGroup = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
            }
            neu.add(famGroup);
        }
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_3; i++) {
            famGroup = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
            }
            neu.add(famGroup);
        }
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_2; i++) {
            NeuBean nb = new NeuBean();
            famGroup = new HashSet<>();
            for (int j = 0; j < 2; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
            }
            neu.add(famGroup);
        }
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_1; i++) {
            famGroup = new HashSet<>();
            for (int j = 0; j < 1; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
            }
            neu.add(famGroup);
        }
    }
    
    private void createCarGroups(int carId) {
        List<NeuBean> neus = new ArrayList<>();
        List<String> shuffleList;
        Queue<String> fx;
        Set<String> famGroup;
        Set<String> prGroup;
        //int size = mbt.keySet().size();
        shuffleList = new ArrayList<>(mbt.keySet());
        
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_4; i++) {
            NeuBean nb = new NeuBean();
            famGroup = new HashSet<>();
            prGroup = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
                prGroup.add(selectRandomPrnFromFamily(fam));
            }
            nb.setFamGroup(famGroup);
            double mass = mass(900000);
            nb.setBack(mass * 0.45);
            nb.setFront(mass * 0.55);
            nb.setId(carId);
            nb.setModel(models[0]);
            nb.setPrGroup(prGroup);
            nb.setTotal(mass);
            neus.add(nb);
        }
        
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_3; i++) {
            NeuBean nb = new NeuBean();
            famGroup = new HashSet<>();
            prGroup = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
                prGroup.add(selectRandomPrnFromFamily(fam));
            }
            nb.setFamGroup(famGroup);
            nb.setBack(0);
            nb.setFront(0);
            nb.setId(carId);
            nb.setModel(models[0]);
            nb.setPrGroup(prGroup);
            nb.setTotal(0);
            neus.add(nb);
        }
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_2; i++) {
            NeuBean nb = new NeuBean();
            famGroup = new HashSet<>();
            prGroup = new HashSet<>();
            for (int j = 0; j < 2; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
                prGroup.add(selectRandomPrnFromFamily(fam));
            }
            nb.setFamGroup(famGroup);
            nb.setBack(0);
            nb.setFront(0);
            nb.setId(carId);
            nb.setModel(models[0]);
            nb.setPrGroup(prGroup);
            nb.setTotal(0);
            neus.add(nb);
        }
        
        shuffleList = new ArrayList<>(fx);
        Collections.shuffle(shuffleList);
        fx = new ArrayDeque<>(shuffleList);
        for (int i = 0; i < MAX_COUNT_1; i++) {
            NeuBean nb = new NeuBean();
            famGroup = new HashSet<>();
            prGroup = new HashSet<>();
            for (int j = 0; j < 1; j++) {
                String fam = fx.poll();
                famGroup.add(fam);
                prGroup.add(selectRandomPrnFromFamily(fam));
            }
            nb.setFamGroup(famGroup);
            nb.setBack(0);
            nb.setFront(0);
            nb.setId(carId);
            nb.setModel(models[0]);
            nb.setPrGroup(prGroup);
            nb.setTotal(0);
            neus.add(nb);
        }
        neus.forEach(System.out::println);
    }
    
    private String selectRandomPrnFromFamily(String fam) {
        Set<String> prFam = mbt.get(fam);
        int size = prFam.size();
        int randIndex = rand.nextInt(size);
        int i = 0;
        String prn = null;
        for (String string : prFam) {
            if (randIndex == i) {
                prn = string;
                break;
            }
            i++;
        }
        return prn;
    }
    
    private double mass(double bound) {
        return rand.nextDouble() * bound;
    }
    
    //private String getFamGroup(Set<String>)
    
}
