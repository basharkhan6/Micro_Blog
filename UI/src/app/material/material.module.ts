import { NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatInputModule} from '@angular/material/input';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';

const material = [
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatToolbarModule,
  MatIconModule,
  FlexLayoutModule,
  MatInputModule,
  MatPaginatorModule,
  MatSidenavModule,
  MatListModule
];

@NgModule({
  imports: [material],
  exports: [material]
})
export class MaterialModule { }
