package school.sptech.EncantoPersonalizados.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import school.sptech.EncantoPersonalizados.core.domain.FotoProduto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Map;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory cf, ObjectMapper objectMapper) {

        // Serializador que converte objetos em JSON
        var json = new GenericJackson2JsonRedisSerializer(objectMapper);

        // Configuração padrão para todos os caches
        var defaults = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(json))
                .disableCachingNullValues()
                .prefixCacheNameWith("app::")
                .entryTtl(Duration.ofMinutes(5)); // TTL padrão de 5 minutos

        // Configurações específicas por cache
        var perCache = Map.ofEntries(
                Map.entry("fotoProdutoById", defaults.entryTtl(Duration.ofMinutes(10))), // TTL maior para fotos
                Map.entry("jogos", defaults.entryTtl(Duration.ofMinutes(10)))
        );

        return RedisCacheManager.builder(cf)
                .cacheDefaults(defaults)
                .withInitialCacheConfigurations(perCache)
                .build();
    }

}

