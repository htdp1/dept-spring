# dept-spring
Department data crud 기능 및 redis cache 처리

## docker build
```
docker build -t dept-spring .
```
```
docker tag dept-spring htdp1/dept-spring:v1
docker push htdp1/dept-spring:v1
```
```
docker tag dept-spring htdp1/dept-spring:latest
docker push htdp1/dept-spring:latest
```

# spring-boot redis 개발

## spring-boot redis 설정
기본으로 spring-boot web project 를 생성하고,
spring-boot redis 설정을 위해서 아래 작업을 진행합니다.
- redis maven dependancy 추가
- application.yml redis 설정
- redis configuration class 생성

### redis maven dependancy 추가
- pom.xml
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    ```
### application.yml redis 설정
- application-redis.yml 파일 생성
    ```yaml
    spring: 
      cache: 
        type: redis
        redis:
        namespace: "htdp1:dept-spring:cache:" # redis key prefix
        ttl: 10                               # time to leave (min)
        host: 127.0.0.1                       # redis host
        port: 7001                            # redis port
        password:                             # redis password
    ```
- application.yml 에 import 처리
    ```yaml
    spring:
      config:
        import:
        - application-redis.yml
    ```
### redis configuration class 생성
- config/CacheConfig.java
    ```java
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

            LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

            return connectionFactory;
        }

        @Bean(name = "cacheManager")
        @Override
        public CacheManager cacheManager() {
            RedisCacheConfiguration configuration = RedisCacheConfiguration
                    .defaultCacheConfig()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair
                            .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                    .prefixCacheNameWith(namespace)
                    .entryTtl(Duration.ofMinutes(ttl));

            RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                    .fromConnectionFactory(redisCacheConnectionFactory())
                    .cacheDefaults(configuration);

            return builder.build();
        }
    }
    ```