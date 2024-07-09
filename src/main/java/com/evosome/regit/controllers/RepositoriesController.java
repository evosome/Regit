package com.evosome.regit.controllers;

import com.evosome.regit.services.git.GitService;
import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repos")
public class RepositoriesController {

    @Autowired
    private RepositoryProvidersLocator locator;

    @GetMapping(value = "/on/{provider}/of/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<RepositoryInfo> getRepositories(
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token
    ) throws RepositoryProviderNotFound {
        var repoProvider = locator.locate(provider);
        return repoProvider.getRepositoriesOf(user, token);
    }

    @GetMapping(value = "/{name}/on/{provider}/of/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    private RepositoryInfo getRepository(
            @PathVariable("name") String name,
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token
    ) throws RepositoryProviderNotFound {
        var repoProvider = locator.locate(provider);
        return repoProvider.getRepository(user, name, token);
    }

}
