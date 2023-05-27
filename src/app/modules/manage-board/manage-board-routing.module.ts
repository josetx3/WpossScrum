import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardSeeComponent } from './pages/board-see/board-see.component';
import { BoardComponent } from './pages/board/board.component';

const routes: Routes = [
  {
    path: '',
    component: BoardSeeComponent,
    //component: BoardComponent,
  },
  {
    path: 'Boardsee',
    component: BoardComponent,
    //component: BoardSeeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ManageBoardRoutingModule {}
