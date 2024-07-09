package com.evosome.regit.services.providers;

import java.util.List;

/**
 * Mapper used to map data from {@link RepositoryCreationInfo} to specific repository creation info.
 * @param <T> Repository info type, receiving from repos hosts
 */
public interface RepositoryCreationMapper<T> {
    T toRepositoryCreationInfo(RepositoryCreationInfo creationInfo);
}
