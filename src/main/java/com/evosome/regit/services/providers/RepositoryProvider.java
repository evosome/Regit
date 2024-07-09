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

    /**
     * Create repository on remote host.
     * @param username User's name
     * @param creationInfo Creating info
     * @param token User's token
     * @return Info about the created repository
     */
    RepositoryInfo createRepository(
            String username,
            RepositoryCreationInfo creationInfo,
            String token);

    /**
     * Helper function to determine whether the repository exists on
     * remote host.
     * @param provider Repository provider
     * @param username User's name
     * @param repoName Repo's name
     * @param token User's token
     * @return Repository info or null, if not exists
     */
    static RepositoryInfo tryGetRepository(
            RepositoryProvider provider,
            String username,
            String repoName,
            String token) {
        try {
            return provider.getRepository(username, repoName, token);
        } catch (Exception ignored) {
            return null;
        }
    }
}
