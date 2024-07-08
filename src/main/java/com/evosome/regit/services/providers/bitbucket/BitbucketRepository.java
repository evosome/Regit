package com.evosome.regit.services.providers.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
class BitbucketRepository {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String gitUrl;

    @JsonProperty("links")
    private void resolveNestedUrl(JsonNode links) {
        gitUrl = links.get("clone").get(0).get("href").asText();
    }

    @JsonProperty("is_private")
    private boolean isPrivate;
}
