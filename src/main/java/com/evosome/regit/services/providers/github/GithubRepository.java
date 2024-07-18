package com.evosome.regit.services.providers.github;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
class GithubRepository {
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("clone_url")
    private String gitUrl;

    @JsonProperty("private")
    private boolean isPrivate;
}
