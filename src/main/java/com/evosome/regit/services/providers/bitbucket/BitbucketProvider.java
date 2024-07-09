package com.evosome.regit.services.providers.bitbucket;

import com.evosome.regit.services.providers.RepositoryCreationInfo;
import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BitbucketProvider implements RepositoryProvider {

    @Autowired
    private BitbucketClient client;

    @Autowired
    private BitbucketRepositoryMapper mapper;

    @Override
    public List<RepositoryInfo> getRepositoriesOf(String username, String token) {
        return mapper.toRepositoryInfoList(client.getPagedRepositories(username, token).getRepositories());
    }

    @Override
    public RepositoryInfo getRepository(String username, String repoName, String token) {
        return mapper.toRepositoryInfo(client.getRepository(username, repoName, token));
    }

    @Override
    public RepositoryInfo createRepository(String username, RepositoryCreationInfo creationInfo, String token) {
        return null;
    }

}
