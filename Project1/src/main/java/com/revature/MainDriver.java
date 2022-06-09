package com.revature;
import java.io.File;
import java.util.List;

import com.revature.controller.Registry;
import com.revature.controller.RequestMapper;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class MainDriver {
	
	public static void main(String args[]) {
		
		
		RequestMapper requestMapper = new RequestMapper();
		
		PrometheusMeterRegistry registry = Registry.registryProm();
		
		
		Javalin app = Javalin.create(
					config -> {
						config.registerPlugin(new MicrometerPlugin(registry));
					}
				).start(7400);
		
		
		requestMapper.configureRoutes(app);
		
		
		
		
		
	}

		
	}

