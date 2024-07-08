package com.evosome.regit.services.git;

import com.evosome.regit.services.git.credentials.DefaultTokenCredentialFactory;
import com.evosome.regit.services.git.credentials.TokenCredentialFactory;
import com.evosome.regit.services.git.properties.GitServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.Map;

@Slf4j
@Service
public class GitService {

    @Autowired
    private GitServiceProperties props;

    @Autowired
    @Qualifier("credentialFactories")
    private Map<String, TokenCredentialFactory> factories;

    public TokenCredentialFactory getTokenCredentialFactory(String gitService) {

        if (!factories.containsKey(gitService))
            return new DefaultTokenCredentialFactory();

        return factories.get(gitService);
    }

    /**
     * Clone repository in the specified path, using user's token.
     * @param name Repository name
     * @param url Repository url to clone
     * @param credentials User credentials
     * @throws GitAPIException when git fails
     */
    public void cloneRepositoryWithToken(
            String name,
            String url,
            CredentialsProvider credentials
    ) throws GitAPIException {

        var path = Paths.get(props.getClonePath(), name);
        try(Git git = Git
                .cloneRepository()
                .setURI(url)
                .setDirectory(path.toFile())
                .setCredentialsProvider(credentials)
                .call()
        ) {
            log.info("Successfully cloned git repository {}", name);
        }
    }
}