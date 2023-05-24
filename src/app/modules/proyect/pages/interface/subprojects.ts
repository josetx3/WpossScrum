export interface Subproject{
  projectId:number;
  subProjectId:number;
  subProjectName:string;
  teamId?: string;
}

export interface SubprojectById{
  projectId:number;
  subProjectId:number;
  subProjectName:string ;
  projectName: string;
}

