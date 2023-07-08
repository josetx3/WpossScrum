import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardComponent } from './pages/board/board.component';
import { BoardEditComponent } from './pages/board-edit/board-edit.component';
import { BoardSeeComponent } from './pages/board-see/board-see.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from "@angular/material/card";
import { MatButtonModule } from '@angular/material/button';
import { ManageBoardRoutingModule } from './manage-board-routing.module';



@NgModule({
  declarations: [
    BoardComponent,
    BoardEditComponent,
    BoardSeeComponent
  ],
  imports: [
    CommonModule,
    ManageBoardRoutingModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule
  ]
})
export class ManageBoardModule { }
