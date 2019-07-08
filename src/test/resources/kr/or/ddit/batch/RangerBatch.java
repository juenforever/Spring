import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.config.spring.ApplicationContext;

public class RangerBatch {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/config/spring/application-batch-dev.xml");
		JobLauncher jobLauncher = (JobLauncher)context.getBean("jobLauncher");
		
		JobLauncher.run(rangerJob,new JobParameters());
	}

}
