package com.example.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.demo.batch.AutobotItemProcessor;
import com.example.demo.batch.JobCompletionNotificationListener;
import com.example.demo.batch.JobCompletionNotificationListenerLogin;
import com.example.demo.batch.LoginItemProcessor;
import com.example.demo.model.Autobot;
import com.example.demo.model.Login;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {
	
	@Autowired
	JobLauncher jobLauncher;

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;
	private final JobCompletionNotificationListener listener;
	private final JobCompletionNotificationListenerLogin listenerlogin;
	

	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			DataSource dataSource, JobCompletionNotificationListener listener, JobCompletionNotificationListenerLogin listenerlogin) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
		this.listener = listener;
		this.listenerlogin = listenerlogin;
	}
	
//	@Scheduled(cron = "*/15 * * * * *")
//	public void runJob1() {
//		Map<String, JobParameter> jobConfigMap = new HashMap<>();
//		jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
//		JobParameters parameters = new JobParameters(jobConfigMap);
//		try {
//			jobLauncher.run(importAutobotJob(), parameters);
//		} catch (Exception ex) {
//			System.err.println(ex.getMessage());
//		}
//	}
	
	@Scheduled(cron = "*/10 * * * * *")
	public void runJob2() {
		Map<String, JobParameter> jobConfigMap = new HashMap<>();
		jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(jobConfigMap);
		try {
			jobLauncher.run(importLoginJob(), parameters);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	
	public FlatFileItemReader<Autobot> reader() {
		FlatFileItemReader<Autobot> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<Autobot>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "name", "car" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Autobot>() {
					{
						setTargetType(Autobot.class);
					}
				});
			}
		});
		return reader;
	}
	
	public FlatFileItemReader<Login> loginReader() {
		FlatFileItemReader<Login> loginReader = new FlatFileItemReader<>();
		loginReader.setResource(new ClassPathResource("teste-dois.csv"));
		loginReader.setLineMapper(new DefaultLineMapper<Login>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "nome", "senha", "grupo" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Login>() {
					{
						setTargetType(Login.class);
					}
				});
			}
		});
		return loginReader;
	}

	@Bean
	public AutobotItemProcessor processor() {
		return new AutobotItemProcessor();
	}
	
	@Bean
	public LoginItemProcessor loginProcessor() {
		return new LoginItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Autobot> writer() {
		JdbcBatchItemWriter<Autobot> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql("INSERT INTO autobot (name, car) VALUES (:name, :car)");
		writer.setDataSource(this.dataSource);
		return writer;
	}
	
	@Bean
	public JdbcBatchItemWriter<Login> loginWriter() {
		JdbcBatchItemWriter<Login> logWriter = new JdbcBatchItemWriter<>();
		logWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		logWriter.setSql("INSERT INTO login (nome, senha, grupo) VALUES (:nome, :senha, :grupo)");
		logWriter.setDataSource(this.dataSource);
		return logWriter;
	}
	
	@Bean
	public Job importLoginJob() {
		return jobBuilderFactory.get("importLoginJob").
				incrementer(new RunIdIncrementer())
				.listener(this.listenerlogin)
				.start(step2())
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Login, Login>chunk(10)
				.reader(loginReader())
				.processor(loginProcessor())
				.writer(loginWriter())
				.build();
	}
	
	@Bean
	public Job importAutobotJob() {
		return jobBuilderFactory.get("importAutobotJob").
				incrementer(new RunIdIncrementer())
				.listener(this.listener)
				.start(step1())
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Autobot, Autobot>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
}
	
