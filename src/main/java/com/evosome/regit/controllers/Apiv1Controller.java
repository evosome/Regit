package com.evosome.regit.controllers;

import com.evosome.regit.services.GitService;
import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Apiv1Controller {

    @Autowired
    private GitService gitService;

    @Autowired
    private RepositoryProvidersLocator locator;

    @GetMapping(value = "/repos/on/{provider}/of/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<RepositoryInfo> getRepositories(
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestParam(value = "access_token", required = false) String token
    ) throws RepositoryProviderNotFound {
        var repoProvider = locator.locate(provider);
        return repoProvider.getRepositoriesOf(user, token);
    }

}
