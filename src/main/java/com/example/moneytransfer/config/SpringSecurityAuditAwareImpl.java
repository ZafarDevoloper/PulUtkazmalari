package com.example.moneytransfer.config;

import com.example.moneytransfer.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Integer> {


    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {

            User user = (User) authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }
}