package com.github.carljmosca;

import com.vaadin.server.VaadinServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZmvApplication {

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//
//        ServletRegistrationBean bean = new ServletRegistrationBean(vaadinServlet, "/*");
//        bean.addInitParameter("UIProvider", "com.github.carljmosca.DemoUIProvider");
//
//        return bean;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ZmvApplication.class, args);
    }

    @Bean
    public VaadinServlet vaadinServlet() {
        return new SpringAwareTouchKitServlet();
    }
}
