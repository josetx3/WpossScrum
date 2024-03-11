import { TeamEmployees } from "./team-employees";

export interface Team {
  areaName: string;
  areaId: string;
  employees?: TeamEmployees[],
  teamId: string;
  teamName: string;
}
