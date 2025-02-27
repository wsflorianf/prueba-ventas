import { Component, OnInit } from '@angular/core';
import {MatTableModule} from '@angular/material/table'
import { ProductoService } from '../../services/producto/producto.service';
import { Producto } from '../../types/apiTypes';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-producto',
  imports: [MatTableModule, CurrencyPipe],
  templateUrl: './producto.component.html',
  styleUrl: './producto.component.css',
})

export class ProductoComponent implements OnInit {
  displayedColumns: string[] = ["id", "nombre", "precio", "stock"]
  dataSource: Producto[] = [];

  constructor(private service: ProductoService) { }
  
  ngOnInit(): void {
    this.service.getProductos().subscribe((data)=>{
      this.dataSource = data
    });
  }
}
