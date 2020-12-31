import { BrowserModule } from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import { HomepageComponent } from './pages/homepage/homepage.component';
import {RouterModule, Routes} from '@angular/router';
import { PostComponent } from './pages/post/post.component';
import { PostViewComponent } from './pages/post-view/post-view.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LoginComponent } from './pages/login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {XhrInterceptor} from './service/xhr-interceptor.service';

const routes: Routes = [
  {path: 'home', component: HomepageComponent},
  {path: 'posts', component: PostComponent},
  {path: 'posts/:id', component: PostViewComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PostComponent,
    PostViewComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
