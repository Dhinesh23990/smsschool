package com.tgi.sd.cron.engine;

import java.util.HashMap;
import java.util.Map;

public class SMSCronExecutor {

	// private static Logger logger = Logger.getLogger(ORECronExecutor.class);

	public static final String CRON_NAME = "cronName";
	public static final String MAIL_TYPE = "mailType";
	public static final String MAIL_STATUS = "mailStatus";
	public static final String CRON_MAX_ROWS = "maxRows";
	public static final int DEFAULT_CRON_MAX_ROWS = 100;

	public void executeBatchJob(Map<String, String> paramMap) throws Exception {
		System.out
				.println("Start - the Cron Job ID " + paramMap.get(CRON_NAME));
		SMSCronBatchJob batchJob = null;
		try {

			System.out.println("--->" + SMSCronBatchJob.jobIdMap);

			System.out.println("--->"
					+ (String) SMSCronBatchJob.jobIdMap.get(paramMap
							.get(CRON_NAME)));
			Object obj = Class.forName(
					(String) SMSCronBatchJob.jobIdMap.get(paramMap
							.get(CRON_NAME))).newInstance();

			System.out.println("Start - Obj");
			batchJob = (SMSCronBatchJob) obj;
			batchJob.initializeCron(paramMap);
			batchJob.loadContext();
			System.out.println("Start - loadCon");
			batchJob.execute();
			System.out.println("Start - the Cron Job ID ");

		} catch (Throwable ex) {
			System.out.println("Failed to start cron....");
			ex.printStackTrace();
		} finally {

			if (batchJob != null) {
				batchJob.destroyContext();
			}
		}
		System.out.println("End - the Cron Job ID " + paramMap.get("cronName"));
	}

	public static void main(String[] args) {

		SMSCronExecutor batchHandler = new SMSCronExecutor();

		if (args != null && args.length > 2) {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put(CRON_NAME, args[0]);
			paramMap.put(MAIL_TYPE, args[1]);
			paramMap.put(MAIL_STATUS, args[2]);
			paramMap.put(CRON_MAX_ROWS, String.valueOf(DEFAULT_CRON_MAX_ROWS));
			try {
				batchHandler.executeBatchJob(paramMap);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				// System.exit(0);
			}
		} else {
			System.out.println("Insufficient arguments to execute this cron.");
			// System.exit(0);
		}

	}

}
