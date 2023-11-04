import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders, HttpErrorResponse
} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class CustomInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (sessionStorage.getItem('auth')) {
      let req = request.clone({
        headers: new HttpHeaders().set('Authorization', sessionStorage.getItem("auth"))
      })
      return next.handle(req);
    } else {
      return next.handle(request);
    }
  }
}
