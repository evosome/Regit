package com.evosome.regit.controllers;

import com.evosome.regit.services.git.GitService;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/sync")
public class SyncController {

    @Autowired
    private GitService git;

    @Autowired
    private RepositoryProvidersLocator locator;

    @PostMapping("{name}/from/{provider}/of/{user}")
    private void syncRepository(
            @PathVariable("name") String name,
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token
    ) throws RepositoryProviderNotFound, GitAPIException, IOException {

        var repoProvider = locator.locate(provider);
        var repository = repoProvider.getRepository(user, name, token);
        var cloneUrl = repository.getGitUrl();

        var credentials = git
                .getTokenCredentialFactory(provider)
                .getCredentialsProvider(user, token);

        git.syncRepository(cloneUrl, name, credentials);
    }

}
