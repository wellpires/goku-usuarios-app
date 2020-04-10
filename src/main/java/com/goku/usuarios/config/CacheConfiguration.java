package com.goku.usuarios.config;

import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

	@Bean
	public CacheManager cacherManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache listarUsuariosCache = new ConcurrentMapCache("listar-usuarios-cache");
		Cache usuarioCache = new ConcurrentMapCache("usuario-cache");
		cacheManager.setCaches(Arrays.asList(listarUsuariosCache, usuarioCache));
		return cacheManager;
	}

}
