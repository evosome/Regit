package com.evosome.regit.services.providers.bitbucket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bitbucket-client", url="${repo-providers.bitbucket.apiUrl}")
interface BitbucketClient {
    @GetMapping("/repositories/{user}")
    BitbucketResponse getPagedRepositories(@PathVariable("user") String username);

    @GetMapping("/repositories/{user}/{name}")
    BitbucketRepository getRepository(
            @PathVariable("user") String username,
            @PathVariable("name") String name);
}
