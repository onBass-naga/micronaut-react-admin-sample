package sample.tasks;

import java.util.concurrent.atomic.AtomicInteger;

public class VulnerableIdGenerator {

    private static AtomicInteger count = new AtomicInteger(1000);

    public static int next() {
        return count.incrementAndGet();
    }
}
