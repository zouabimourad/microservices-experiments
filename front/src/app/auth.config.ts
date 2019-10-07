import {AuthConfig} from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {

  loginUrl: 'http://localhost:8089/oauth/authorize',
  redirectUri: window.location.origin + '/#/home',
  clientId : 'public',
  scope: 'read',
  oidc: false

};
