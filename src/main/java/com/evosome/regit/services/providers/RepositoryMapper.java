package com.evosome.regit.services.providers;

import java.util.List;

/**
 * Mapper used to map data from repository info (received from repos hosts) to {@link RepositoryInfo}.
 * @param <T> Repository info type, receiving from repos hosts
 */
public interface RepositoryMapper<T> {
    RepositoryInfo toRepositoryInfo(T other);
    List<RepositoryInfo> toRepositoryInfoList(List<T> other);
}
