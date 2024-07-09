package com.evosome.regit.controllers;

import com.evosome.regit.services.git.GitService;
import com.evosome.regit.services.providers.RepositoryCreationInfo;
import com.evosome.regit.services.providers.RepositoryProvider;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private GitService git;

    @Autowired
    private RepositoryProvidersLocator locator;

    @PostMapping("{name}/to/{provider}/of/{user}")
    private void syncRepository(
            @PathVariable("name") String name,
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestParam(value = "private", defaultValue = "false") boolean isPrivate,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token
    ) throws RepositoryProviderNotFound, GitAPIException, IOException {

        var repoProvider = locator.locate(provider);
        var repository = RepositoryProvider.tryGetRepository(repoProvider, user, name, token);

        if (repository == null)
            repository = repoProvider.createRepository(
                    user,
                    RepositoryCreationInfo
                            .builder()
                            .name(name)
                            .isPrivate(isPrivate)
                            .build(),
                    token
            );

        var gitUrl = repository.getGitUrl();

        var credentials = git
                .getTokenCredentialFactory(provider)
                .getCredentialsProvider(user, token);

        git.uploadRepository(name, gitUrl, credentials);
    }

}
