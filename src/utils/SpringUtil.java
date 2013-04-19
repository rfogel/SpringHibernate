package utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringUtil.ctx = ctx;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}

}
