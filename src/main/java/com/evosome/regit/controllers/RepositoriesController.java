package com.evosome.regit.controllers;

import com.evosome.regit.services.providers.RepositoryInfo;
import com.evosome.regit.services.providers.RepositoryProvidersLocator;
import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Repositories controller",
        description =
                "Repository controller allows to get list of repositories " +
                "from remote hosts (like GitHub, BitBucket and etc.)"
)
@RestController
@RequestMapping("/api/v1/repos")
public class RepositoriesController {

    @Autowired
    private RepositoryProvidersLocator locator;

    @Operation(
            summary = "Resolve list of repositories",
            description =
                    "Resolve list of repositories from the remote repos host. You have" +
                    "specify access token to resolve both public and private repositories",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully resolved repositories list"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "User not authorized, as his token either expired or not valid"
                    )
            }
    )
    @GetMapping(value = "/on/{provider}/of/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<RepositoryInfo> getRepositories(
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader("X-Auth-Token") String token
    ) throws RepositoryProviderNotFound {
        var repoProvider = locator.locate(provider);
        return repoProvider.getRepositoriesOf(user, token);
    }

    @Operation(
            summary = "Resolve the certain repository",
            description =
                    "Resolve the certain repository from the remote repos host. You have" +
                            "specify access token to resolve both public and private repositories",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully resolved repositories list"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "User not authorized, as his token either expired or not valid"
                    )
            }
    )
    @GetMapping(value = "/{name}/on/{provider}/of/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    private RepositoryInfo getRepository(
            @PathVariable("name") String name,
            @PathVariable("provider") String provider,
            @PathVariable("user") String user,
            @RequestHeader("X-Auth-Token") String token
    ) throws RepositoryProviderNotFound {
        var repoProvider = locator.locate(provider);
        return repoProvider.getRepository(user, name, token);
    }

}
