package io.github._4drian3d.vpacketlogger;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import io.github._4drian3d.vpacketlogger.listener.PacketListener;
import io.github._4drian3d.vpacketlogger.modules.ConfigurationModule;
import org.slf4j.Logger;

@Plugin(
	id = "vpacketlogger",
	name = "VPacketLogger",
	description = "Simple plugin to log Packet executions",
	version = Constants.VERSION,
	authors = { "4drian3d" },
	dependencies = { @Dependency(id = "vpacketevents") }
)
public final class VPacketLogger {
	@Inject
	private EventManager eventManager;
	@Inject
	private Logger logger;
	@Inject
	private Injector injector;
	
	@Subscribe
	void onProxyInitialization(final ProxyInitializeEvent event) {
		logger.info("Starting VPacketLogger...");
		injector = injector.createChildInjector(new ConfigurationModule());
		eventManager.register(this, injector.getInstance(PacketListener.class));
	}
}