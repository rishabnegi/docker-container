package com.poc.spring;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;

@SpringBootApplication
@EnableWebSecurity(debug = true)
@EnableCaching
@EnableTransactionManagement
@Configuration
public class JwtSampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(JwtSampleApplication.class, args);
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("READ")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean("SFC2")// turn of sessionId generation
	public SecurityFilterChain filterChaisnS(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/h2-console").permitAll()
				.antMatchers("/readme").permitAll()
				.antMatchers("/login").authenticated()
				.antMatchers("/authn").authenticated()
				.and()
				.httpBasic();
		http.csrf().disable();   //h2 support
		http.headers().frameOptions().disable();//h2 support
		http.addFilterAfter(new TestFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}

/*
	@Bean
	public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){

		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		//First, we solve the problem of key serialization
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//The default serializer of the RedisTemplate class is JdkSerializationRedisSerializer
		redisTemplate.setKeySerializer(stringRedisSerializer);

		//Solve the serialization problem of value
       */
/* Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);*//*


		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
				new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();


		return redisTemplate ;
	}

	@Bean("springCM")
	public CacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.disableCachingNullValues()
				.entryTtl(Duration.ofHours(1))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
		redisCacheConfiguration.usePrefix();

		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
				.cacheDefaults(redisCacheConfiguration).build();
	}
*/

}
