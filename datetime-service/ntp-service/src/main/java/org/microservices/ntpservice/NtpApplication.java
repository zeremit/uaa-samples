package org.microservices.ntpservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 *
 */
@SpringBootApplication
public class NtpApplication {
    /**
     * @param args - args
     */
    public static void main(String[] args) {
        SpringApplication.run(NtpApplication.class, args);
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Value("${oauth.checkToken}")
        private String checkTokenUrl;

        @Value("${oauth.clientId}")
        private String clientId;

        @Value("${oauth.clientSecret}")
        private String clientSecret;


        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('time.read')")
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('time.write')")
                    .anyRequest().permitAll(); //[4]
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//            resources.resourceId("datetimeapp");
        }

        @Bean
        ResourceServerTokenServices tokenService(){
            RemoteTokenServices remoteUAA = new RemoteTokenServices();
            remoteUAA.setClientId(clientId);
            remoteUAA.setClientSecret(clientSecret);
            remoteUAA.setCheckTokenEndpointUrl(checkTokenUrl);
            return remoteUAA;
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServer2 extends ResourceServerConfigurerAdapter {

        @Value("${oauth.checkToken}")
        private String checkTokenUrl;

        @Value("${oauth.clientId}")
        private String clientId;

        @Value("${oauth.clientSecret}")
        private String clientSecret;


        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('time.read')")
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('time.write')")
                    .anyRequest().permitAll(); //[4]
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(null);
        }

        @Bean
        ResourceServerTokenServices tokenService(){
            RemoteTokenServices remoteUAA = new RemoteTokenServices();
            remoteUAA.setClientId(clientId);
            remoteUAA.setClientSecret(clientSecret);
            remoteUAA.setCheckTokenEndpointUrl(checkTokenUrl);
            return remoteUAA;
        }
    }
}
