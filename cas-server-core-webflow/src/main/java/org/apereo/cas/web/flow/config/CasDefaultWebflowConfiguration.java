package org.apereo.cas.web.flow.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.DefaultWebflowConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * This is {@link CasDefaultWebflowConfiguration}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Configuration("casCoreWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CasDefaultWebflowConfiguration {

    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowDefinitionRegistry;

    @Autowired
    private FlowBuilderServices flowBuilderServices;

    @Autowired
    private CasConfigurationProperties casProperties;

    @ConditionalOnMissingBean(name="defaultWebflowConfigurer")
    @Bean
    public CasWebflowConfigurer defaultWebflowConfigurer() {
        final DefaultWebflowConfigurer c = new DefaultWebflowConfigurer();
        c.setLoginFlowDefinitionRegistry(loginFlowDefinitionRegistry);
        c.setFlowBuilderServices(flowBuilderServices);
        return c;
    }
}
