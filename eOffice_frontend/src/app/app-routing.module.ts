import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeComponent, EditEmployeeComponent } from 'src/app/employee/employee.component'
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { DepartmentComponent } from './department/department.component';
import { AddDepartmentComponent } from './add-department/add-department.component';
import { RegisterComponent } from './register/register.component';
import { EditDepartmentComponent } from './add-department/edit-department.component';

const routes: Routes = [
  {path:'emps',component:EmployeeComponent},
  {path:'addEmp',component:AddEmployeeComponent},
  {path:'editEmp',component:EditEmployeeComponent},
  {path:'depts',component:DepartmentComponent},
  {path:'addDept',component:AddDepartmentComponent},
  {path:'editDept',component:EditDepartmentComponent},
  {path:'login',component:LoginComponent},
  {path:'logout',component:LogoutComponent},
  {path:'register',component:RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
