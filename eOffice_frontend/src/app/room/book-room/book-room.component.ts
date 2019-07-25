import { Component, OnInit, Inject } from '@angular/core';
import { Emp, Room, RoomTicket } from 'src/app/model/model.component';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClientService } from 'src/app/service/http-client.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-book-room',
  templateUrl: './book-room.component.html',
  styleUrls: ['./book-room.component.css']
})
export class BookRoomComponent implements OnInit {
  ticket:RoomTicket;
  room:Room;
  rooms:Room[];
  admin:Emp;
  emp:Emp;
  emps:Emp[];
  sDate = new Date;
  eDate = new Date;

  startTime: NgbTimeStruct = {hour: 12, minute: 0, second: 0};
  endTime: NgbTimeStruct = {hour: 12, minute: 30, second: 0};
  hourStep = 1;
  minuteStep = 30;

  minDate = new Date;
  // datePipe:DatePipe;
  constructor(private datePipe: DatePipe,
              private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<BookRoomComponent>,
              public dialogWarn: MatDialog,
              public dialogWarnRef: MatDialogRef<WarningForRebook>,
              @Inject(MAT_DIALOG_DATA) public c_ticket: RoomTicket) { 
                this.ticket = c_ticket;

                this.emp = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: sessionStorage.getItem('username'),
                  dept: null
                }
                this.ticket.emp = this.emp;

                this.admin = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: '',
                  dept: null
                }
                
                this.room = {
                  roomId: 0,
                  roomName: '',
                  roomType: '',
                  roomCapacity: 0
                }

                this.ticket.room = this.room;
              }

  ngOnInit() {
    this.httpClientService.getEmps().subscribe(response => this.successEmpResponse(response));
    this.httpClientService.getRooms().subscribe(response => this.successRoomResponse(response));
  }
  
  successEmpResponse(response) {
    return this.emps = response;
  }

  successRoomResponse(response) {
    return this.rooms = response;
  }

  public createTicket(){    
    this.ticket.room = this.room;
    this.ticket.admin = this.admin;
    console.log(this.admin);
    console.log(this.eDate);
    console.log(this.datePipe.transform(this.eDate, "yyyy-MM-dd"));

    this.ticket.startTime = this.datePipe.transform(this.sDate, "yyyy-MM-dd");
    this.ticket.endTime = this.datePipe.transform(this.eDate, "yyyy-MM-dd");

    if (this.startTime.hour < 10){
      this.ticket.startTime += ' 0' + this.startTime.hour;  
    } else {
      this.ticket.startTime += ' ' + this.startTime.hour;
    }

    if (this.startTime.minute < 10){
      this.ticket.startTime += ':0' + this.startTime.minute;
    } else {
      this.ticket.startTime += ':' + this.startTime.minute;
    }

    if (this.endTime.hour < 10){
      this.ticket.endTime += ' 0' + this.endTime.hour;  
    } else {
      this.ticket.endTime += ' ' + this.endTime.hour;
    }

    if (this.endTime.minute < 10){
      this.ticket.endTime += ':0' + this.endTime.minute;
    } else {
      this.ticket.endTime += ':' + this.endTime.minute;
    }
    

    console.log(this.ticket);
    
    this.httpClientService.sendRoomTicket(this.ticket)
    .subscribe(data=>{
          console.log(data);
          if (data.emp.username != this.ticket.emp.username){
            this.openWarningWindow(data)  
          } else {
            this.dialogRef.close();
            this.router.navigate(["/rooms"]);
          }
    })
    
  }

  openWarningWindow(respond: RoomTicket): void {
    const dialogWarnRef = this.dialogWarn.open(WarningForRebook, {
      width: '350px',
      data: respond
    });

    dialogWarnRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}


@Component({
  selector: 'app-warn-book-room',
  templateUrl: './warning-for-rebook.component.html',
  styleUrls: ['./book-room.component.css']
})
export class WarningForRebook {
  constructor(
    public dialogRef: MatDialogRef<WarningForRebook>,
    @Inject(MAT_DIALOG_DATA) public data: RoomTicket) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}