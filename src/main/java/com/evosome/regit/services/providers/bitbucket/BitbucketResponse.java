package com.evosome.regit.services.providers.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
class BitbucketResponse {

    @JsonProperty("values")
    private List<BitbucketRepository> repositories;
}
