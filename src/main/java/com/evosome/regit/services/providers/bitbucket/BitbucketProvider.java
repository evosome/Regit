package com.evosome.regit.services.providers.bitbucket;

import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BitbucketProvider implements RepositoryProvider {

    @Autowired
    private BitbucketClient client;

    @Autowired
    private BitbucketRepositoryMapper mapper;

    @Override
    public List<RepositoryInfo> getRepositoriesOf(String username, String token) {
        return mapper.toRepositoryInfoList(client.getPagedRepositories(username).getRepositories());
    }

    @Override
    public RepositoryInfo getRepository(String username, String repoName, String token) {
        return mapper.toRepositoryInfo(client.getRepository(username, repoName));
    }

}
