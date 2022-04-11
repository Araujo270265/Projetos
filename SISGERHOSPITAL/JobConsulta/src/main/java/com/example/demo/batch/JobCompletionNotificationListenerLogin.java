package com.example.demo.batch;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.model.Login;

@Component
public class JobCompletionNotificationListenerLogin extends JobExecutionListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListenerLogin.class);
	
    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListenerLogin(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
     public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<Login> results = this.jdbcTemplate.query("SELECT nome, senha, grupo FROM login",
                    (rs, row) -> new Login(rs.getString(1), rs.getString(2), rs.getInt(3)));

            for (Login login : results) {
                log.info("Found <" + login.toString() + "> in the database.");
            }

        }
    }

}
