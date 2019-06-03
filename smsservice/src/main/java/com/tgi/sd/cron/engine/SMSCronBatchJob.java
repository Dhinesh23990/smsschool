package com.tgi.sd.cron.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class SMSCronBatchJob {


	public ClassPathXmlApplicationContext context = null;
	public ArrayList<String> contextBeans = new ArrayList<>();
	public String cronName = null;
	public String mailType = null;
	public String mailStatus = null;
	public int maxRows = 0;
	public Date cronStartTime = null;
	public Map<String, String> paramMap = new HashMap<String, String>();

	public static HashMap<String, String> jobIdMap = new HashMap<String, String>();
	long startTime = 0L;
	long startFreeMemory = 0L;
	public static Date _startDate = null;

	public static final String CRON_CONTEXT_XML = "batchProcess.xml";
	
	public static final String CRON_NAME = "cronName";
	public static final String MAIL_TYPE = "mailType";
	public static final String MAIL_STATUS = "mailStatus";
	public static final String CRON_MAX_ROWS = "maxRows";
	public static final int DEFAULT_CRON_MAX_ROWS = 100;

	static {
		jobIdMap.put("MailJob", "com.sd.mail.MailJob");
	}

	public static Map<String, String> getCronBatches() {
		return jobIdMap;
	}

	public void initializeCron(Map<String, String> paramMap) {
		this.paramMap = paramMap;
		cronName = paramMap.get(CRON_NAME);
		mailType = paramMap.get(MAIL_TYPE);
		mailStatus = paramMap.get(MAIL_STATUS);
		if (paramMap.get(CRON_MAX_ROWS) != null) {
			maxRows = Integer.parseInt(paramMap.get(CRON_MAX_ROWS));
		} else {
			maxRows = DEFAULT_CRON_MAX_ROWS;
		}
	}

	public void loadContext() throws Exception {
		try {
			System.out.println(CRON_CONTEXT_XML);
			contextBeans.add(CRON_CONTEXT_XML);
			context = new ClassPathXmlApplicationContext(
					contextBeans.toArray(new String[0]));
			startTime = System.currentTimeMillis();
			startFreeMemory = Runtime.getRuntime().freeMemory();
			System.out.println("startTime : " + startTime);
			System.out.println("startFreeMemory : "
					+ humanReadableByteCount((startFreeMemory), true));

		} catch (Exception ex) {
			System.out.println(" Exception while loading context..." + ex);
			throw ex;
		}
	}

	public void calculateProcessTimeAndMemoryLoad() {
		long endTime = System.currentTimeMillis();
		long endFreeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("\n endTime : " + endTime);
		System.out.println("endFreeMemory : " + endFreeMemory);
		String timeDiff = String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes(endTime - startTime),
				TimeUnit.MILLISECONDS.toSeconds(endTime - startTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(endTime - startTime)));
		System.out.println("\n time difference : " + timeDiff);
		System.out.println(" memory difference : "
				+ (humanReadableByteCount((startFreeMemory - endFreeMemory),
						true)));
	}

	private String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1)
				+ (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public abstract void execute() throws Exception;

	public void destroyContext() throws Exception {

		try {
			if (context != null) {
				calculateProcessTimeAndMemoryLoad();
				context.close();
			}
		} catch (Exception e) {
		}

	}

}
