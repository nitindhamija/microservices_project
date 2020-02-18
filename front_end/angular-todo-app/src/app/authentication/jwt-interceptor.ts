import { Injectable } from '@angular/core';
import {HttpRequest,HttpHandler,HttpEvent,HttpInterceptor} from '@angular/common/http';
import {Observable } from 'rxjs';

@Injectable()
export class jwtInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>,
              next: HttpHandler): Observable<HttpEvent<any>> {

        const idToken = localStorage.getItem("token");
    // const idToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXRpbmRoYW1pamEiLCJzY29wZXMiOiJST0xFX2FkbWluLFJPTEVfZGV2ZWxvcGVyIiwiaWF0IjoxNTU5NjM2OTczLCJleHAiOjE1NTk2NTQ5NzN9.lz9xpdPn2zak0Goac4DwBNg0pEVyu9Ccltnq1UTi7Q0";
        if (idToken) {
            const cloned = req.clone({
                headers: req.headers.set("Authorization",
                    "Bearer " + idToken)
            });

            return next.handle(cloned);
        }
        else {
            return next.handle(req);
        }
    }
}