package com.evosome.regit.services.providers.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BitbucketRepositoryCreationInfo {

    @JsonProperty("scm")
    private String schema;
    private boolean is_private;
}
