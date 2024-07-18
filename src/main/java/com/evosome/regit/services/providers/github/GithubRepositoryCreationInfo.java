package com.evosome.regit.services.providers.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class GithubRepositoryCreationInfo {

    private String name;

    @JsonProperty("private")
    private boolean isPrivate;

}
