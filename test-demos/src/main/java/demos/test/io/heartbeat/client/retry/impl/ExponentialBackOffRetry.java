package demos.test.io.heartbeat.client.retry.impl;

import demos.test.io.heartbeat.client.retry.RetryPolicy;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by xiedong
 * Date: 2023/5/25 22:15
 */
@Slf4j
public class ExponentialBackOffRetry implements RetryPolicy {
    private static final int MAX_RETRIES_LIMIT = 29;
    private static final int DEFAULT_MAX_SLEEP_MS = Integer.MAX_VALUE;

    private final Random random = new Random();
    private final long baseSleepTimeMs;
    private final int maxRetries;
    private final int maxSleepMs;

    public ExponentialBackOffRetry(int baseSleepTimeMs, int maxRetries) {
        this(baseSleepTimeMs, maxRetries, DEFAULT_MAX_SLEEP_MS);
    }

    public ExponentialBackOffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs) {
        this.maxRetries = maxRetries;
        this.baseSleepTimeMs = baseSleepTimeMs;
        this.maxSleepMs = maxSleepMs;
    }

    @Override
    public boolean allowRetry(int retryCount) {
        if (retryCount < maxRetries) {
            return true;
        }
        return false;
    }

    @Override
    public long getSleepTimeMs(int retryCount) {
        if (retryCount < 0) {
            throw new IllegalArgumentException("retries count must greater than 0.");
        }
        if (retryCount > MAX_RETRIES_LIMIT) {
//            System.out.println(String.format("maxRetries too large (%d). Pinning to %d", maxRetries, MAX_RETRIES_LIMIT));
            log.info("ExponentialBackOffRetry:getSleepTimeMs:47,超过最大重试次数限制【{}】，将使用：【{}】", maxRetries, MAX_RETRIES_LIMIT);
            retryCount = MAX_RETRIES_LIMIT;
        }
        long sleepMs = baseSleepTimeMs * Math.max(1, random.nextInt(1 << retryCount));
        if (sleepMs > maxSleepMs) {
            log.info("ExponentialBackOffRetry:getSleepTimeMs:52,休眠时间超过最大限制【{}】，将使用：【{}】", maxRetries, MAX_RETRIES_LIMIT);
//            System.out.println(String.format("Sleep extension too large (%d). Pinning to %d", sleepMs, maxSleepMs));
            sleepMs = maxSleepMs;
        }
        return sleepMs;
    }

}
