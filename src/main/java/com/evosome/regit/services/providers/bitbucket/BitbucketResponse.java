package com.evosome.regit.services.providers.bitbucket;

import lombok.Data;

import java.util.List;

@Data
class BitbucketResponse {
    private List<BitbucketRepository> repositories;
}
