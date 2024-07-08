package com.evosome.regit.services.providers.github;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "github-client", url = "${repo-providers.github.apiUrl}")
interface GithubClient {

    @GetMapping("/user/repos")
    List<GithubRepository> getRepositoriesOf(
            @RequestHeader("Authorization") String token);

    @GetMapping("/repos/{user}/{name}")
    GithubRepository getRepository(
            @PathVariable("user") String username,
            @PathVariable("name") String name,
            @RequestHeader("Authorization") String token);
}
