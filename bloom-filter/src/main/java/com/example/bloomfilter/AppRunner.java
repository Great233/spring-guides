package com.example.bloomfilter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.BitSet;

/**
 * @author Great
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final int DEFAULT_SIZE = 2 << 24;

    private static final int[] SEEDS = {3, 13, 46, 71, 91, 134};

    private BitSet bitSet = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    public AppRunner() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(Object value) {
        for (SimpleHash f :
                func) {
            bitSet.set(f.hash(value), true);
        }
    }

    public boolean contains(Object value) {
        for (SimpleHash f :
                func) {
            int index = f.hash(value);
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    class SimpleHash {

        private int capacity;

        private int seed;

        public SimpleHash(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }

        public int hash(Object value) {
            int h;
            return value == null
                    ? 0
                    : Math.abs(seed * (capacity - 1) & ((h = value.hashCode()) ^ h >>> 16));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        String value1 = "asfsdfasdf";
        String value2 = "owoe923i43";
        String value3 = ",/.,.adkwe";
        String value4 = "2309023";

        add(value1);
        add(value2);
        add(value3);
        add(value4);

        System.out.println(contains(value1));
        System.out.println(contains("asdxzcsqw"));
    }
}
