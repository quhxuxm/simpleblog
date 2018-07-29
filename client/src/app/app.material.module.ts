import { NgModule } from '@angular/core';
import {
  MatIconModule,
  MatListModule,
  MatSidenavModule,
  MatToolbarModule,
  MatTableModule,
  MatButtonModule,
  MatCardModule
} from '@angular/material';

import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [MatSidenavModule, MatToolbarModule, MatIconModule,
    MatListModule, FlexLayoutModule, MatCardModule,
    MatButtonModule,
    MatTableModule],
  exports: [MatSidenavModule, MatToolbarModule, MatIconModule,
    MatListModule, FlexLayoutModule, MatCardModule,
    MatButtonModule,
    MatTableModule]
})
export class AppMaterialModule {
}
