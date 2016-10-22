# UAA Application Example

## Goals
1. Understand Cloudfoundry UAA
2. Learn how to code resource-server and client with spring boot

## Pre reading that helps
1. [UAA Docs](https://github.com/cloudfoundry/uaa)

2. [UAA API Doc](https://docs.cloudfoundry.org/api/uaa/)

3. [Spring OAuth 2.0](http://projects.spring.io/spring-security-oauth/docs/Home.html)

4. [UAAC](https://github.com/cloudfoundry/cf-uaac)

## Application Components

### Resource Server


  1. It needs to register as a UAA 2 clients.

  ```
    
    
           timeapp:
            client_id: timeapp
            resource_ids: none
            scope: time.read,time.write
            authorities: uaa.resource
            secret: timeappclientsecret
            authorized-grant-types: authorization_code, refresh_token
            name: The Ultimate TimeApp
          timezoneapp:
            client_id: timezoneapp
            resource_ids: none
            scope: timezone.read,timezone.write
            authorities: uaa.resource
            secret: timezoneappclientsecret
            authorized-grant-types: authorization_code,refresh_token
            name: The Ultimate DateTimeApp

  ```
  
  ### Client
  
  It needs to register as a UAA client.
  
  ```
  
  
            datetimeapp:
              scope: time.read,timezone.read,time.write,timezone.write
              authorities: uaa.resource
              resource_ids: none
              secret: datetimeappclientsecret
              authorized-grant-types: implicit,authorization_code,client_credentials
              signup_redirect_url: http://localhost:8080/,http://api-gateway.cf-dev.altoros.com/
              name: The Ultimate DateTimeApp
  ```