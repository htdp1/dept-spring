package com.htdp1.deptspring.config;

import com.htdp1.deptspring.dept.model.DepartmentRedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.protocol.ProtocolVersion;

@Configuration
@EnableRedisRepositories
public class RepositoryConfig {

	public @Value("${spring.repository.redis.host}") String host;
	public @Value("${spring.repository.redis.port}") int port;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);

		ClientOptions clientOptions = ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build();

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().clientOptions(clientOptions)
				.build();

		LettuceConnectionFactory connectionFactory = new
		LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
		connectionFactory.setValidateConnection(false);
		connectionFactory.afterPropertiesSet();

		return connectionFactory;
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(DepartmentRedis.class));
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
}
