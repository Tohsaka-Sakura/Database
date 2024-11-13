package com.hospital.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        try {

            Map<String, Object> claims = JwtUtil.verifyToken(token);
            String id = stringRedisTemplate.opsForValue().get(claims.get("id").toString());

            String redisToken = stringRedisTemplate.opsForValue().get(id);

            if(redisToken == null || !redisToken.equals(token)) {
                throw new RuntimeException();
            }


            ThreadLocalUtil.set(claims);
            return true;

        } catch (Exception e) {
            response.setStatus(401);

            return false;
        }

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Map<String, Object> claims = ThreadLocalUtil.get();
        String id = claims.get("id").toString();

        stringRedisTemplate.opsForValue().getOperations().delete(id);

        ThreadLocalUtil.remove();

    }
}
