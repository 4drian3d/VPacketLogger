package io.github._4drian3d.vpacketlogger.listener;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.ProxyServer;
import io.github._4drian3d.vpacketevents.api.event.PacketReceiveEvent;
import io.github._4drian3d.vpacketevents.api.event.PacketSendEvent;
import io.github._4drian3d.vpacketlogger.configuration.Configuration;
import org.slf4j.Logger;

public class PacketListener {
    @Inject
    private Configuration configuration;
    @Inject
    private Logger logger;

    @Subscribe
    void onReceiveEvent(final PacketReceiveEvent event) {
        if (!event.getResult().isAllowed()) {
            return;
        }
        final StringBuilder builder = new StringBuilder();
        for (String line : configuration.receiveFormat()) {
            builder.append(line).append('\n');
        }
        logger.info(builder.toString());
    }

    @Subscribe
    void onSentEvent(final PacketSendEvent event) {
        if (!event.getResult().isAllowed()) {
            return;
        }
        final StringBuilder builder = new StringBuilder();
        for (String line : configuration.sentFormat()) {
            builder.append(line).append('\n');
        }
        logger.info(builder.toString());
    }
}
