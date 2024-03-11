export interface IBoard {
  idBoard: string,
  date: Date,
  teamId: string,
  userStoryId: string,
  taskTeamId: string,
  taskName: string,
  employeeId: string
  employeeName: string,
  taskHours: string,
  taskState: string
}

export interface Board {
  [userStoryId: string]: any; // Aquí, el tipo 'any' se puede reemplazar con el tipo adecuado para las user stories
}
