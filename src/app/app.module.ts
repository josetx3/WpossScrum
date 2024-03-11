import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { LandingPageComponent } from './layout/landing-page/landing-page.component';
import { MainComponent } from './layout/main/main.component';
import { SidenavComponent } from './layout/sidenav/sidenav.component';
import { SharedModule } from './shared/shared.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { FilterAddEmployeesTeamPipe } from './pipes/pages/filter-employees-add-team/filter-add-employees-team.pipe';
import { MatDialogModule } from '@angular/material/dialog';
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatLineModule} from "@angular/material/core";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { TokenExpirationInterceptor } from './interceptors/token-expiration.interceptor';
import { NgxChartsModule } from '@swimlane/ngx-charts';




@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    HeaderComponent,
    SidenavComponent,
    MainComponent,
    FooterComponent,
    FilterAddEmployeesTeamPipe,

  ],
  imports: [

    CommonModule,
    BrowserModule,
    AppRoutingModule,

    //modules
    FormsModule,
    SharedModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxChartsModule,

    //Material
    MatSidenavModule,
    MatLineModule,
    MatMenuModule,
    MatGridListModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatDialogModule


  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: PathLocationStrategy,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass:TokenExpirationInterceptor,
      multi: true
    }
  ],
  exports: [FilterAddEmployeesTeamPipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
