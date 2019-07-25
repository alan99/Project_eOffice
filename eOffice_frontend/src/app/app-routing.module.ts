import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeComponent, EditEmployeeComponent } from './employee/employee.component'
import { AddEmployeeComponent } from './employee/add-employee/add-employee.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { DepartmentComponent } from './department/department.component';
import { AddDepartmentComponent } from './department/add-department/add-department.component';
import { RegisterComponent } from './register/register.component';
import { EditDepartmentComponent } from './department/add-department/edit-department.component';
import { TaskComponent } from './task/task.component';
import { AddTaskComponent } from './task/add-task/add-task.component';
import { RoomComponent } from './room/room.component';
import { BookRoomComponent, WarningForRebook } from './room/book-room/book-room.component';

const routes: Routes = [
  {path:'emps',component:EmployeeComponent},
  {path:'addEmp',component:AddEmployeeComponent},
  {path:'editEmp',component:EditEmployeeComponent},
  {path:'depts',component:DepartmentComponent},
  {path:'addDept',component:AddDepartmentComponent},
  {path:'editDept',component:EditDepartmentComponent},
  {path:'tasks',component:TaskComponent},
  {path:'addtask',component:AddTaskComponent},
  {path:'rooms',component:RoomComponent},
  {path:'bookRoom',component:BookRoomComponent},
  {path:'WarnForRebook',component:WarningForRebook},
  {path:'login',component:LoginComponent},
  {path:'logout',component:LogoutComponent},
  {path:'register',component:RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
