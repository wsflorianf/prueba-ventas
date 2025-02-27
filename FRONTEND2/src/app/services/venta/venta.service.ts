import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, mergeMap } from 'rxjs';
import { NuevaVenta, Venta } from '../../types/apiTypes';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private apiUrl = 'http://localhost:8080/api/venta';

  constructor(private http: HttpClient) { }

  getVentas(): Observable<Venta[]>{
    return this.http.get<Venta[]>(this.apiUrl)
  }

  nuevaVenta(data: NuevaVenta): Observable<Venta>{
    return this.http.post<Venta>(this.apiUrl, data)
  }

  editarVenta(id: number, data: NuevaVenta): Observable<Venta>{
    return this.http.put<Venta>(this.apiUrl+`/${id}`, data)
  }

  eliminarVenta(id: number): Observable<boolean>{
    return this.http.delete(this.apiUrl+`/${id}`, {observe: 'response'}).pipe(map((response: any) => {
      
      console.log(response)
      return response.status === 204;
    }))
  }
}
