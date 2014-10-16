/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.sync.diffsync;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * {@link ShadowStore} implementation that stores shadows in Redis, via an injected {@link RedisTemplate}.
 * 
 * @author Craig Walls
 */
public class RedisShadowStore implements ShadowStore {

	private RedisOperations<String, Object> redisTemplate;

	/**
	 * Constructs a Redis-based {@link ShadowStore}.
	 * @param redisTemplate a {@link RedisOperations} that will be used to store shadow copies.
	 */
	public RedisShadowStore(RedisOperations<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void putShadow(String key, Object shadow) {
		redisTemplate.opsForValue().set(key, shadow);
	}

	@Override
	public Object getShadow(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
