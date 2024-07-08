package com.evosome.regit.services.providers.github;

import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GithubProvider implements RepositoryProvider {

    @Autowired
    private GithubClient client;

    @Autowired
    private GithubRepositoryMapper mapper;

    @Override
    public List<RepositoryInfo> getRepositoriesOf(String username, String token) {
        return mapper.toRepositoryInfoList(client.getRepositoriesOf(username));
    }

    @Override
    public RepositoryInfo getRepository(String username, String repoName, String token) {
        return mapper.toRepositoryInfo(client.getRepository(username, repoName));
    }

}
