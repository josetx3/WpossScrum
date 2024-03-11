export interface Employee {
  employeeCharge: string,
  employeeEmail: string,
  employeeId: string,
  employeeKnowledge: string,
  employeeName: string,
  employeePassword?: string,
  checked?:boolean
}

export interface Employee2 {
  employeeCharge: string,
  employeeEmail: string,
  employeeId: string,
  employeeKnowledge: string,
  employeeName: string,
  employeeNewPassword?: string,
  employeeCurrentPassword?:string,
  checked?:boolean
}
