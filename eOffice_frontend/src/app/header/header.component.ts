import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { WeatherApiService, WeatherResponse } from '../service/weather-api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  c_weather: WeatherResponse;

  constructor(private loginService: AuthenticationService,
    private weatherService: WeatherApiService) { }

  ngOnInit() {
    // this.weatherService.getWeather().subscribe(response => {
      
    //     this.c_weather = response.body;
      
    //    console.log('testtest'); 
    // }
    // );
  }

  successResponse(response) {
    console.log(22, response);
    this.c_weather = response;
  }

}
