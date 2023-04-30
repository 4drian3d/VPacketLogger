package io.github._4drian3d.vpacketlogger.modules;

import com.google.common.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import io.github._4drian3d.vpacketlogger.configuration.Configuration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class PluginModule extends AbstractModule {

    @Singleton
    @Provides
    private Configuration configuration(final @DataDirectory Path dataDirectory) throws Throwable {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .setPath(dataDirectory.resolve("config.conf"))
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
