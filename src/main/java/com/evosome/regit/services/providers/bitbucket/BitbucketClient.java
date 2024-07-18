package com.evosome.regit.services.providers.bitbucket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="bitbucket-client", url="${repo-providers.bitbucket.apiUrl}")
interface BitbucketClient {
    @GetMapping("/repositories/{user}?access_token={token}")
    BitbucketResponse getPagedRepositories(
            @PathVariable("user") String username,
            @PathVariable("token") String token
    );

    @GetMapping("/repositories/{user}/{name}?access_token={token}")
    BitbucketRepository getRepository(
            @PathVariable("user") String username,
            @PathVariable("name") String name,
            @PathVariable("token") String token);

    @PostMapping("/repositories/{user}/{name}?access_token={token}")
    BitbucketRepository createRepository(
            @PathVariable("user") String username,
            @PathVariable("name") String name,
            @PathVariable("token") String token,
            @RequestBody BitbucketRepositoryCreationInfo creationInfo
    );
}
