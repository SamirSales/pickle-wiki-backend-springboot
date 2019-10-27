package io.github.samirsales.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelRunner extends BlockJUnit4ClassRunner {

    public ParallelRunner(Class<?> klass) throws InitializationError {
        super(klass);
        setScheduler(new ThreadPool());
    }

    private static class  ThreadPool implements RunnerScheduler{
        private final int NUMBER_OF_THREADS = 3;
        private final int TIMEOUT_TO_FINISH_IN_MINUTES = 10;
        private ExecutorService executorService;

        @SuppressWarnings("WeakerAccess")
        public ThreadPool() {
            executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        }

        @Override
        public void schedule(Runnable runnable) {
            executorService.submit(runnable);
        }

        @Override
        public void finished() {
            executorService.shutdown();

            try {
                executorService.awaitTermination(TIMEOUT_TO_FINISH_IN_MINUTES, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
}
