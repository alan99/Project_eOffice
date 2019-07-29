import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { EmployeeComponent, EditEmployeeComponent } from './employee/employee.component';
import { AddEmployeeComponent } from './employee/add-employee/add-employee.component';
import { HttpClientService } from './service/http-client.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component'
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';

import { MatDialogModule, MatButtonModule, MatSortModule, MatDatepickerModule, MatNativeDateModule, MatToolbarModule, MatButtonToggleModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material';
import { DepartmentComponent } from './department/department.component';
import { AddDepartmentComponent } from './department/add-department/add-department.component';
import { RegisterComponent } from './register/register.component';
import { MatTableModule } from '@angular/material/table';
import { ModelComponent } from './model/model.component';
import { EditDepartmentComponent } from './department/add-department/edit-department.component';
import { TaskComponent } from './task/task.component';
import { AddTaskComponent } from './task/add-task/add-task.component';
import { DatePipe } from '@angular/common';
import { RoomComponent } from './room/room.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BookRoomComponent, WarningForRebook } from './room/book-room/book-room.component';
import { LeaveManagementComponent } from './leave-management/leave-management.component';
import { LeaveFormComponent } from './leave-form/leave-form.component';



@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    AddEmployeeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    EditEmployeeComponent,
    DepartmentComponent,
    AddDepartmentComponent,
    EditDepartmentComponent,
    TaskComponent,
    RegisterComponent,
    ModelComponent,
    AddTaskComponent,
    RoomComponent,
    BookRoomComponent,
    WarningForRebook,
    LeaveManagementComponent,
    LeaveFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatTabsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatToolbarModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgbModule
  ],
  entryComponents: [
    EditEmployeeComponent
  ],
  providers: [
    HttpClientService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHttpInterceptorService,
      multi: true
    },
    DatePipe
  ],
  bootstrap: [AppComponent],
  exports: [
    MatSortModule,
    MatDatepickerModule
  ]
})
export class AppModule { }
