import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author xiaorui
 */
public class Main {
    private static final int NUM_OF_THREADS = 5;
    private static final int NUM_OF_IDS_PER_THREAD = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            int workerId = i + 1;
            executorService.execute(() -> generateIds(workerId));
        }
        executorService.shutdown();
    }

    private static void generateIds(int workerId) {
        Snowflake snowflake = new Snowflake(workerId);
        for (int i = 0; i < NUM_OF_IDS_PER_THREAD; i++) {
            long id = snowflake.generateId();
            System.out.println("Worker ID: " + workerId + ", Generated ID: " + id);
        }
    }
}
