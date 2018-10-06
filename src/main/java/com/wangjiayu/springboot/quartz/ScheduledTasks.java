package com.wangjiayu.springboot.quartz;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasks {

	@Scheduled(cron = "* * 2 * * * ")
	public void reportCurrentByCron() {
		System.out.println("Scheduling Tasks Examples By Cron: The time is now " + new Date());
	}

}
