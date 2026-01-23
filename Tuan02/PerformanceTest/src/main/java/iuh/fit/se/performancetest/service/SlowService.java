package iuh.fit.se.performancetest.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SlowService {

    public String processWithoutCache() {
        simulateHeavyWork();
        return "Result no cache";
    }

    @Cacheable(value = "perf-test")
    public String processWithCache() {
        simulateHeavyWork();
        return "Result with cache";
    }

    private void simulateHeavyWork() {
        try {
            int delay = 1000;
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
