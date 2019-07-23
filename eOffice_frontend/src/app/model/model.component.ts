import { Component, OnInit } from '@angular/core';

export interface Dept {
  deptId: number;
  deptName: string;
}

export interface Emp {
  empId: number;
  f_Name: string;
  l_Name: string;
  contactNo: number;
  username: string;
  dept: Dept;
}

export interface Task {
  taskId: number;
  taskName: string;
  taskText: string;
  startDate: string;
  endDate: string;
  leader: Emp;
  emp: Emp;
  taskStatus: string;
}

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
  styleUrls: ['./model.component.css']
})
export class ModelComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}