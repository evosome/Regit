package com.evosome.regit.controllers;

import com.evosome.regit.services.git.GitService;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(
        name = "Sync controller",
        description =
                "Synchronize local repositories or clone new ones from remote repo hosts."
)
@RestController
@RequestMapping("/api/v1/sync")
public class SyncController {

    @Autowired
    private GitService git;

    @Autowired
    private RepositoryProvidersLocator locator;

    @Operation(
            summary = "Synchronize repository",
            description =
                    "Synchronize local repo or clone new from remote host",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully resolved repositories list"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created remote repository and uploaded"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "User not authorized, as his token either expired or not valid"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server-side exception happened. Basically, git exceptions happens"
                    )
            }
    )
    @PostMapping("{name}/from/{provider}/of/{user}")
    private ResponseEntity<?> syncRepository(
            @PathVariable("name") String name,
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader("X-Auth-Token") String token
    ) throws RepositoryProviderNotFound, GitAPIException, IOException {

        var repoProvider = locator.locate(provider);
        var repository = repoProvider.getRepository(user, name, token);
        var cloneUrl = repository.getGitUrl();
        var wasNotCreated = !git.repositoryExists(name);

        var credentials = git
                .getTokenCredentialFactory(provider)
                .getCredentialsProvider(user, token);

        git.syncRepository(cloneUrl, name, credentials);

        return new ResponseEntity<>(
                wasNotCreated
                        ? HttpStatus.CREATED
                        : HttpStatus.OK
        );
    }

}
