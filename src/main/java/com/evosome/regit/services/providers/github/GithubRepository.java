package com.evosome.regit.services.providers.github;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
class GithubRepository {
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String url;

    @JsonProperty("private")
    private boolean isPrivate;
}
