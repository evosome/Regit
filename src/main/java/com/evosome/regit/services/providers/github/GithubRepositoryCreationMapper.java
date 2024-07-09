package com.evosome.regit.services.providers.github;

import com.evosome.regit.services.providers.RepositoryCreationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GithubRepositoryCreationMapper extends RepositoryCreationMapper<GithubRepositoryCreationInfo> { }
