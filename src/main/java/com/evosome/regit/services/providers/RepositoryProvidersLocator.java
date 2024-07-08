package com.evosome.regit.services.providers;

import com.evosome.regit.services.providers.exceptions.RepositoryProviderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RepositoryProvidersLocator {

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("providers")
    private Map<String, Class<? extends RepositoryProvider>> providersMap;

    public RepositoryProvider locate(String providerName) throws RepositoryProviderNotFound {

        Class<? extends RepositoryProvider> providerType = providersMap.get(providerName);

        if (providerType == null)
            throw new RepositoryProviderNotFound("unable to find '" + providerName + "' repository provider.");

        return context.getBean(providerType);
    }

}
