package com.codecake.spotify_clone_be.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.codecake.spotify_clone_be.userContext.repository",
        "com.codecake.spotify_clone_be.catalogcontext.repository"
})
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {
}
