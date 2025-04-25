package com.adi.Async.Config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAsync
public class AppConfig implements AsyncConfigurer {

    /* We have JAVA Thread Pool Executor
        * Properties :
            * Min Pool Size , MAx Pool Size , Queue Size
        * Working :
            * First All the tasks are assigned to current Threads i.e Min Pool Size
            * Then for new Tasks if no Thread available Queue gets filled
            * When Queue is filled new Tasks are assigned to new Threads
            * These threads are created until it reaches MAX pool size
            * When new Tasks comes now Exception occurs
    * */

    @Bean(name = "adiSpringBootThreadPoolExecutor")
    public Executor taskPoolExecutorSP(){

        int minPoolSize = 2;
        int maxPoolSize = 4;
        int queueSize = 3;

        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        // SpringBoot
        poolTaskExecutor.setCorePoolSize(minPoolSize);
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        poolTaskExecutor.setQueueCapacity(queueSize);
        poolTaskExecutor.setThreadNamePrefix("Adi SP Thread-");
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }

    @Bean(name = "adiJavaThreadPoolExecutor")
    public Executor taskPoolExecutorPJ(){

        int minPoolSize = 2;
        int maxPoolSize = 4;
        int queueSize = 3;

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(minPoolSize
                ,maxPoolSize,1, TimeUnit.HOURS
                ,new ArrayBlockingQueue<>(queueSize)
                ,new CustomThreadFactory());
        // JAVA

        return poolExecutor;
    }

    class CustomThreadFactory implements ThreadFactory{

        private final AtomicInteger threadNo = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread th = new Thread(r);
            th.setName("Adi Java Thread- " + threadNo.getAndIncrement());
            return th;
        }
    }

    private ThreadPoolExecutor poolExecutor;
    @Override
    public synchronized Executor getAsyncExecutor(){

        if(poolExecutor == null){
            int minPoolSize = 2;
            int maxPoolSize = 4;
            int queueSize = 3;

            poolExecutor = new ThreadPoolExecutor(minPoolSize
                    ,maxPoolSize,1, TimeUnit.HOURS
                    ,new ArrayBlockingQueue<>(queueSize)
                    ,new CustomCommonThreadFactory());
            // JAVA + SP
        }

        return poolExecutor;
    }

    class CustomCommonThreadFactory implements ThreadFactory{

        private final AtomicInteger threadNo = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread th = new Thread(r);
            th.setName("Adi (Java+SP) Thread- " + threadNo.getAndIncrement());
            return th;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Autowired
    AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler;

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return this.asyncUncaughtExceptionHandler;
    }

}


@Component
class DefaultAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler{

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.err.println("❌ Error: " + ex.getMessage());
    }
    // CAlled only when void Async is runned
}
