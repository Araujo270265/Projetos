package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulingTask {
	
		
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //private static final String zoneBR = "America/Sao_Paulo";
   // private static final String everyTenSeconds = "0/30 * * * * *";
   // private static final long oneMinute = 60000;
 
    @Scheduled(cron = "*/5 * * * * *")
   //@Scheduled(cron = everyTenSeconds, zone = zoneBR)
   public void scheduleFutureTaskOne() {
      System.out.println("O Horario Atual: " + dateFormat.format(new Date()));
	  
      
   }
   
    @Scheduled(cron = "*/15 * * * * *")
   //@Scheduled(fixedDelay = oneMinute, zone = zoneBR)
   public void scheduleFutureTaskTwo() {
      System.out.println("O segundo Horario Atual: " + dateFormat.format(new Date()));
   }

}
