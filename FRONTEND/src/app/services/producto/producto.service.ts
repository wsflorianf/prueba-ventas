import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../../types/apiTypes';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  
  private apiUrl = 'http://localhost:8080/api/producto';

  constructor(private http: HttpClient) { }

  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.apiUrl)
  }
}
