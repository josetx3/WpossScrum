export interface UserStory {
  subProjectId: number;
  userStoryId: number;
  userStoryStateId: number;
  userStoryArchive: string;
  userStoryName: string;
  fechaMaxima?: Date;
  userStoryScore: number;
}

export interface SubprojectUserStory {
  userStoryName:string;
  userStoryStateName:string;
  fechaMaxima:string;
  userStoryScore:string;
}

export interface UserStoryToTeam {
  userStoryId: string;
  userStoryName: string;
  userStoryScore: number;
  userStoryArchive: string;
  userStoryStateId: number;
  fechaMaxima?: Date;
  userStoryStateName: string;
  subProjectId: string;
}
