package io.github._4drian3d.vpacketlogger.listener;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import io.github._4drian3d.velocityhexlogger.HexLogger;
import io.github._4drian3d.vpacketevents.api.event.PacketReceiveEvent;
import io.github._4drian3d.vpacketevents.api.event.PacketSendEvent;
import io.github._4drian3d.vpacketlogger.configuration.Configuration;
import io.github._4drian3d.vpacketlogger.resolver.PacketResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public final class PacketListener {
    @Inject
    private Configuration configuration;
    @Inject
    private HexLogger logger;

    @Subscribe
    void onReceiveEvent(final PacketReceiveEvent event) {
        if (!event.getResult().isAllowed()) {
            return;
        }

        final PacketResolver resolver = new PacketResolver(event);
        final TextComponent.Builder builder = Component.text();
        for (final String line : configuration.receiveFormat()) {
            builder.append(miniMessage().deserialize(line, resolver), Component.newline());
        }
        logger.info(builder.build());
    }

    @Subscribe
    void onSentEvent(final PacketSendEvent event) {
        if (!event.getResult().isAllowed()) {
            return;
        }

        final PacketResolver resolver = new PacketResolver(event);
        final TextComponent.Builder builder = Component.text();

        for (final String line : configuration.sentFormat()) {
            builder.append(miniMessage().deserialize(line, resolver), Component.newline());
        }
        logger.info(builder.build());
    }
}
