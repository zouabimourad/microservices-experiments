import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {Observable} from 'rxjs';
import {OAuthService} from "angular-oauth2-oidc";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private oauthService: OAuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.oauthService.getAccessToken()}`
      }
    });

    return next.handle(request);
  }
}
