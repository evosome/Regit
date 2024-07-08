package com.evosome.regit.services.providers;

import java.util.List;

/**
 * Abstract repository provider for resolving repository information from
 * repositories hosts (like GitHub, Bitbucket and etc.)
 */
public interface RepositoryProvider {
    /**
     * Resolve a list of all repositories of the specified user. It's possible to pass
     * access token to resolve private repositories.
     * @param username name of the user
     * @param token access token to resolve private repos
     * @return list of user's repos
     */
    List<RepositoryInfo> getRepositoriesOf(String username, String token);

    /**
     * Resolve only one repository of the specified user.
     * @param username name of the user
     * @param repoName name of the repository
     * @param token access token to resolve private repos
     * @return user's repo
     */
    RepositoryInfo getRepository(String username, String repoName, String token);
}
