import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

// export interface Weather {
//   main: string,
//   description: string
// }

export class Main {
  temp: number;
  pressure: number;
  humidity: number;
  temp_min: number;
  temp_max: number;
}

export class WeatherResponse {
  main: Main;
  base: string;
  visibility: number;
}

// let config = {
//   headers: {
//     "Content-Type": "application/json",
//     // 'Access-Control-Allow-Origin': '*',
//     }
//   }

  const HttpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': 'my-auth-token'
    })
  }
  
@Injectable({
  providedIn: 'root'
})
export class WeatherApiService {
  private static readonly WEATHER_URL='https://api.openweathermap.org/data/2.5/weather?zip=15241,us';
  private static readonly WEATHER_API_KEY = '7fb35e7786f6146a20c6ec641e8824ff';

  constructor(private httpClient:HttpClient) { }

  getWeather():Observable<HttpResponse<WeatherResponse>>{
    let link = WeatherApiService.WEATHER_URL + '&appid=' + WeatherApiService.WEATHER_API_KEY;
    console.log(121, link);
    return this.httpClient.get<WeatherResponse>(link, {observe: 'response'});
  }
}
