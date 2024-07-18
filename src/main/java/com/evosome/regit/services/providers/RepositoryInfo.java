package com.evosome.regit.services.providers;

import lombok.Data;

/**
 * Represents a small set of common information fields of repositories, presented by
 * repositories hosts.
 */
@Data
public class RepositoryInfo {
    private String name;
    private String fullName;
    private String gitUrl;
    private boolean isPrivate;
}
