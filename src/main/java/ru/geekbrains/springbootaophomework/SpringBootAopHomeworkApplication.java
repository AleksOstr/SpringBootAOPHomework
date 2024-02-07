package ru.geekbrains.springbootaophomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAopHomeworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootAopHomeworkApplication.class);

		FirstBean firstBean = context.getBean(FirstBean.class);
		SecondBean secondBean = context.getBean(SecondBean.class);

		firstBean.firstMethod();
		secondBean.secondMethod();

		System.out.println(secondBean.thirdMethod());

		secondBean.fourthMethod();
	}

}
