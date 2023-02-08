package com.example.demo.endoints;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.info.InfoContributorAutoConfiguration;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class CustomInfoEndPoint implements InfoContributor {
    InfoContributorAutoConfiguration con;
	
	@Autowired
	ConfigurableApplicationContext ctx;
	@Override
	public void contribute(Builder builder) {
		
		Map<String,Object> details=new HashMap<>();
		details.put("totalBeans", ctx.getBeanDefinitionCount());
		details.put("start", ctx.getStartupDate());
        builder.withDetails(details);
	}

}
