package kr.or.ddit.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "kr.or.ddit.board" }, useDefaultFilters = true)
public class ApplicationIocBeanScanConfig {

}
