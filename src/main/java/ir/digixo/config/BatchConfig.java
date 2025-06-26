package ir.digixo.config;

import ir.digixo.processor.MyItemProcessor;
import ir.digixo.reader.MyItemReader;
import ir.digixo.writer.MyItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    final private JobRepository jobRepository;
    final private PlatformTransactionManager platformTransactionManager;
    final private MyItemReader myItemReader;
    final private MyItemProcessor myItemProcessor;
    final private MyItemWriter myItemWriter;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, MyItemReader myItemReader, MyItemProcessor myItemProcessor, MyItemWriter myItemWriter) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.myItemReader = myItemReader;
        this.myItemProcessor = myItemProcessor;
        this.myItemWriter = myItemWriter;
    }

    @Bean
    public Job firstJob() {
        return new JobBuilder("First Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep1())
                .build();
    }

    @Bean
    public Step chunkStep1() {
        return new StepBuilder("First Chunk Step", jobRepository)
                .<Integer, Long>chunk(3, platformTransactionManager)
                .reader(myItemReader)
                .writer(myItemWriter)
                .processor(myItemProcessor)
                .build();
    }

}
