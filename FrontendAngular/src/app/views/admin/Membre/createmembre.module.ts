import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 

import { CreateMembreComponent } from './createmembre.component';

@NgModule({
  declarations: [
    CreateMembreComponent
  ],
  imports: [
    CommonModule,
    FormsModule 
  ]
})
export class CreateMembreModule { }
