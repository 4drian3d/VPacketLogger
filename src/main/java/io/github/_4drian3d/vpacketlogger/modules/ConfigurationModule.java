package io.github._4drian3d.vpacketlogger.modules;

import com.google.common.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import io.github._4drian3d.vpacketlogger.configuration.Configuration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("UnstableApiUsage")
public final class ConfigurationModule extends AbstractModule {

    @Singleton
    @Provides
    private Configuration configuration(final @DataDirectory Path dataDirectory) throws Throwable {
        if (Files.notExists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        final Path configPath = dataDirectory.resolve("config.conf");
        if (Files.notExists(configPath)) {
            try (InputStream is = Configuration.class.getResourceAsStream("/config.conf")) {
                Files.copy(Objects.requireNonNull(is), configPath);
            }
        }
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .setPath(configPath)
                .build();

        final ConfigurationNode node = loader.load();
        final TypeToken<String> stringToken = TypeToken.of(String.class);
        final List<String> sentFormat = node.getNode("sent-format").getList(stringToken, List.of());
        final List<String> receiveFormat = node.getNode("receive-format").getList(stringToken, List.of());

        return new Configuration() {
            @Override
            public List<String> sentFormat() {
                return sentFormat;
            }

            @Override
            public List<String> receiveFormat() {
                return receiveFormat;
            }
        };
    }
}
