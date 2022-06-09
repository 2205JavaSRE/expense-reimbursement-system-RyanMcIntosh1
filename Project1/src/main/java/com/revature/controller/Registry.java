package com.revature.controller;

import java.io.File;

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

public class Registry {
	public static PrometheusMeterRegistry registryProm() {
	PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	
	registry.config().commonTags("application","Project-1");
	
	new ClassLoaderMetrics().bindTo(registry);
	new JvmMemoryMetrics().bindTo(registry);
	new JvmGcMetrics().bindTo(registry);
	new JvmThreadMetrics().bindTo(registry);
	new UptimeMetrics().bindTo(registry);
	new ProcessorMetrics().bindTo(registry);
	new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
	
	
	//Building a custom metric!
	
	Counter loginCounter = Counter
			.builder("path_attempts_of_logins")
			.description("Amount of Logins")
			.tag("purpose", "traffic")
			.register(registry);
	
	Counter managerDecisionCounter = Counter
			.builder("path_manager_decisions")
			.description("Amount of Decisions Made")
			.tag("purpose", "admin work")
			.register(registry);
	
	Counter viewCounter = Counter
			.builder("path_views")
			.description("Amount of Views")
			.tag("purpose", "see amount of views made")
			.register(registry);

	
	
	return registry;
	
}
	public Counter loginCount(PrometheusMeterRegistry registry) {
		Counter loginCounter = Counter
				.builder("path_attempts_of_logins")
				.description("Amount of Logins")
				.tag("purpose", "traffic")
				.register(registry);
		return loginCounter;
	}
	public Counter managerDecisionCount(PrometheusMeterRegistry registry) {
		Counter managerDecisionCounter = Counter
				.builder("path_manager_decisions")
				.description("Amount of Decisions Made")
				.tag("purpose", "admin work")
				.register(registry);
		return managerDecisionCounter;
	}
	public Counter viewCount(PrometheusMeterRegistry registry) {
		Counter viewCounter = Counter
				.builder("path_views")
				.description("Amount of Views")
				.tag("purpose", "see amount of views made")
				.register(registry);
		return viewCounter;
	}
}
