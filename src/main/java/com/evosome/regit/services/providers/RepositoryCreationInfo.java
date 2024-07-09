package com.evosome.regit.services.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Small set of information field needed to create repository.
 */
@Data
@Builder
@AllArgsConstructor
public class RepositoryCreationInfo {
    private String name;
    private boolean isPrivate;
}
