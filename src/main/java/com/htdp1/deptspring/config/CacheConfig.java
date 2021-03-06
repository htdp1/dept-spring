package com.htdp1.deptspring.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.protocol.ProtocolVersion;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	public @Value("${spring.cache.redis.host}") String host;
	public @Value("${spring.cache.redis.port}") int port;
	public @Value("${spring.cache.redis.namespace}") String namespace;
	public @Value("${spring.cache.redis.ttl}") long ttl;

	@Bean(name = "redisCacheConnectionFactory")
	public RedisConnectionFactory redisCacheConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);

		ClientOptions clientOptions = ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build();

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().clientOptions(clientOptions)
				.build();

		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);

		return connectionFactory;
	}

	@Bean(name = "cacheManager")
	@Override
	public CacheManager cacheManager() {

		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()))
				.prefixCacheNameWith(namespace) // cache key prefix
				.entryTtl(Duration.ofMinutes(ttl)) // time-to-live
		;

		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisCacheConnectionFactory()) // connection factory
				.cacheDefaults(configuration) // cache configuration
		;

		return builder.build();
	}
}
