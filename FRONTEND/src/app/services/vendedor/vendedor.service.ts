import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendedor } from '../../types/apiTypes';

@Injectable({
  providedIn: 'root'
})
export class VendedorService {

  private apiUrl = 'http://localhost:8080/api/vendedor';

  constructor(private http: HttpClient) { }

  getVendedores(): Observable<Vendedor[]>{
    return this.http.get<Vendedor[]>(this.apiUrl)
  }
}
