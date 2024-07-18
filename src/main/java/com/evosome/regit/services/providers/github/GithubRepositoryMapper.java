package com.evosome.regit.services.providers.github;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.evosome.regit.services.providers.RepositoryMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface GithubRepositoryMapper extends RepositoryMapper<GithubRepository> { }
