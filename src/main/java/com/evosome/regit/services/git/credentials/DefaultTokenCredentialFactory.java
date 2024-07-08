package com.evosome.regit.services.git.credentials;

import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class DefaultTokenCredentialFactory extends TokenCredentialFactory {
    @Override
    public CredentialsProvider getCredentialsProvider(String username, String token) {
        return new UsernamePasswordCredentialsProvider(username, token);
    }
}
