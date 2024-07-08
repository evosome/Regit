package com.evosome.regit.services.git.credentials;

import org.eclipse.jgit.transport.CredentialsProvider;

/**
 * Factory for all credentials via token.
 */
public abstract class TokenCredentialFactory {
    public abstract CredentialsProvider getCredentialsProvider(String username, String token);
}
