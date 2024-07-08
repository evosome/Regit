package com.evosome.regit.services.providers.config;

import com.evosome.regit.services.providers.RepositoryProvider;
import com.evosome.regit.services.providers.bitbucket.BitbucketProvider;
import com.evosome.regit.services.providers.github.GithubProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RepositoryProviderLocatorConfiguration {

    @Bean
    public Map<String, Class<? extends RepositoryProvider>> providers() {
        return Map.ofEntries(
                Map.entry("github", GithubProvider.class),
                Map.entry("bitbucket", BitbucketProvider.class)
        );
    }

}
