package com.evosome.regit.services.git;

import com.evosome.regit.services.git.credentials.DefaultTokenCredentialFactory;
import com.evosome.regit.services.git.credentials.TokenCredentialFactory;
import com.evosome.regit.services.git.properties.GitServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
     * Sync (clone or update already existing repo) repository in the specified path, using user's token.
     * If directory exists on the specified path and directory is a git repo, try to update it.
     * If directory exists on the specified path and directory is not a git repo, throw error.
     * @param name Repository name
     * @param url Repository url to clone
     * @param credentials User credentials
     * @throws GitAPIException when git fails
     */
    public void syncRepository(
            String url,
            String name,
            CredentialsProvider credentials
    ) throws GitAPIException, IOException {

        var path = Paths.get(props.getClonePath(), name);
        var pathAsFile = path.toFile();
        var gitPathAsFile = new File(pathAsFile, ".git");

        // if repository exists, update it.
        if (RepositoryCache.FileKey.isGitRepository(gitPathAsFile, FS.DETECTED)) {
            pull(pathAsFile, credentials);
        }
        // otherwise try to clone into the following path. If path already exists, and it's not a git repo,
        // throw error.
        else {
            clone(pathAsFile, url, credentials);
        }
    }

    private void clone(File path, String url, CredentialsProvider credentials) throws GitAPIException {
        try(Git git = Git
                .cloneRepository()
                .setURI(url)
                .setDirectory(path)
                .setCloneAllBranches(true)
                .setCredentialsProvider(credentials)
                .call()
        ) {
            log.info("Successfully cloned git repository from '{}' on '{}'", url, path);
        }
    }

    private void pull(
            File path,
            CredentialsProvider credentials
    ) throws IOException, GitAPIException {

        try (Git git = Git.open(path)) {
            git
                    .pull()
                    .setCredentialsProvider(credentials)
                    .call();
            log.info("Successfully pulled git repository {}", path);
        }

    }
}