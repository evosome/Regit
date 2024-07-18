package com.evosome.regit.services.providers.bitbucket;

import com.evosome.regit.services.providers.RepositoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BitbucketRepositoryMapper extends RepositoryMapper<BitbucketRepository> { }
