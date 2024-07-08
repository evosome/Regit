package com.evosome.regit.services.providers.bitbucket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
