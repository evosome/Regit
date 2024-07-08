package com.evosome.regit.services.git;

import com.evosome.regit.services.git.properties.GitServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class GitService {

    private final GitServiceProperties props;

    @Autowired
    public GitService(GitServiceProperties props) { this.props = props; }

    /**
     * Clone repository in the specified path, using user's token.
     * @param url Repository url to clone
     * @param token User token
     * @throws GitAPIException when git fails
     */
    public void cloneRepositoryWithToken(String name, String url, String token) throws GitAPIException {
        try(Git git = Git
                .cloneRepository()
                .setURI(url)
                .setDirectory(
                        new File(props.getClonePath() + "/" + name))
                .setCredentialsProvider(
                        new UsernamePasswordCredentialsProvider(token, ""))
                .call()
        ) {
            log.info("Successfully cloned git repository {}", name);
        }
    }
}