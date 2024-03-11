import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterAddEmployeesTeam'
})
export class FilterAddEmployeesTeamPipe implements PipeTransform {

  transform(value: any, ...args: []): any {
    return null;
  }

}
