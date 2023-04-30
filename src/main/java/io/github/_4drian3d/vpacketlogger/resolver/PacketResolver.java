package io.github._4drian3d.vpacketlogger.resolver;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import io.github._4drian3d.vpacketevents.api.event.PacketReceiveEvent;
import io.github._4drian3d.vpacketevents.api.event.PacketSendEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record PacketResolver(Player player, MinecraftPacket packet) implements TagResolver {
    public PacketResolver(PacketSendEvent event) {
        this(event.getPlayer(), event.getPacket());
    }

    public PacketResolver(PacketReceiveEvent event) {
        this(event.getPlayer(), event.getPacket());
    }

    @Override
    public @Nullable Tag resolve(
            final @NotNull String name,
            final @NotNull ArgumentQueue arguments,
            final @NotNull Context ctx
    ) throws ParsingException {
        return switch (name) {
            case "player" -> Tag.selfClosingInserting(Component.text(player.getUsername()));
            case "packet" -> Tag.preProcessParsed(packet.getClass().getSimpleName());
            case "is_native" -> Tag.preProcessParsed(Boolean.toString(packet.getClass().getPackageName().startsWith("com.velocitypowered.proxy.protocol.packet")));
            case "class" -> Tag.preProcessParsed(packet.getClass().toString());
            default -> null;
        };
    }

    @Override
    public boolean has(final @NotNull String name) {
        return name.equals("packet")
            || name.equals("player")
            || name.equals("is_native")
            || name.equals("class");
    }
}
