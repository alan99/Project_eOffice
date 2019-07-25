import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { RoomTicket, Emp } from '../model/model.component';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { BookRoomComponent } from './book-room/book-room.component';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  tickets:RoomTicket[];
  emp:Emp;
  displayedColumns: string[] = ['id', 'room', 'startTime', 'endTime', 'admin', 'emp', 'status', 'actionsColumn'];

  constructor(private httpClientService: HttpClientService,
              private dialog: MatDialog,
              private loginService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.httpClientService.getRoomTickets().subscribe(response => this.successResponse(response));
    console.log(this.tickets);
  }

  successResponse(response) {
    return this.tickets = response;
  }

  openBookRoomDialog(): void {
    let ticket:RoomTicket = { ticketId: 0,
                              room: null,
                              startTime: '',
                              endTime: '',
                              admin: null,
                              emp: null,
                              description: '',
                              status: ''          }

    const dialogRef = this.dialog.open(BookRoomComponent, {
      width: '450px',
      data: ticket
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public respondTicket(ticket): void {
    ticket.status = "Approved";
    this.httpClientService.updateRoomTicket(ticket).subscribe();
  }

  

  public deleteTicket(ticket): void {
    this.httpClientService.deleteTicket(ticket)
      .subscribe(data => {
        this.tickets = this.tickets.filter(a => a !== ticket);
        this.router.navigate(["/tickets"]);
      });
  }
}
