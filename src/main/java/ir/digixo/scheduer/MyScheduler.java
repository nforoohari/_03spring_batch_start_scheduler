package ir.digixo.scheduer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyScheduler {

    final private JobLauncher jobLauncher;
    final private Job firstJob;

    public MyScheduler(JobLauncher jobLauncher, Job firstJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
    }

    //    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void myJobStarter() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("mytime", System.currentTimeMillis())
                .toJobParameters();
        System.out.println("Job started!!!");
        jobLauncher.run(firstJob, jobParameters);
    }
}
