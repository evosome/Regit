package com.evosome.regit.services.git.config;

import com.evosome.regit.services.git.credentials.BitbucketTokenCredentialFactory;
import com.evosome.regit.services.git.credentials.DefaultTokenCredentialFactory;
import com.evosome.regit.services.git.credentials.TokenCredentialFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class TokenCredentialsConfiguration {

    @Bean
    public Map<String, TokenCredentialFactory> credentialFactories() {
        return Map.ofEntries(
                Map.entry("github", new DefaultTokenCredentialFactory()),
                Map.entry("bitbucket", new BitbucketTokenCredentialFactory())
        );
    }

}
