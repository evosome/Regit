package com.evosome.regit.services.git.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Component
@ConfigurationProperties("git-service")
public class GitServiceProperties {

    @Value("clone-path")
    private String clonePath;

}
