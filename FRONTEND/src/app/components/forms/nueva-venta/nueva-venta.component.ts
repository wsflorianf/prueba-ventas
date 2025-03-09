import { Component, Inject, OnInit } from '@angular/core';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Producto, Vendedor, Venta } from '../../../types/apiTypes';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { ProductoService } from '../../../services/producto/producto.service';
import { VendedorService } from '../../../services/vendedor/vendedor.service';
import { NgFor } from '@angular/common';
import { VentaService } from '../../../services/venta/venta.service';

@Component({
  selector: 'app-nueva-venta',
  templateUrl: './nueva-venta.component.html',
  styleUrls: ['./nueva-venta.component.css'],
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatInputModule,
    NgFor,
  ],
})
export class NuevaVentaComponent implements OnInit {
  ventaForm!: FormGroup;
  vendedores: Vendedor[] = [];
  productos: Producto[] = [];

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private vendedorService: VendedorService,
    private ventaService: VentaService,
    public dialogRef: MatDialogRef<NuevaVentaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {   

    this.productoService.getProductos().subscribe((data) => {
      this.productos = data;
    });

    this.vendedorService.getVendedores().subscribe((data) => {
      this.vendedores = data;
    });

    this.ventaForm = this.fb.group({
      vendedorId: ['', Validators.required],
      productoId: ['', Validators.required],
      fecha: ['', Validators.required],
      cantidad: ['', [Validators.required, Validators.min(1)]],
    });

    if (this.data) {
      this.data.vendedorId = this.data.vendedor.id;
      this.data.productoId = this.data.producto.id;
      
      this.ventaForm.patchValue(this.data);
    }
  }

  guardarVenta(): void {
    if (this.ventaForm.valid) {
      if (this.data) {
        this.ventaService.editarVenta(this.data.id, this.ventaForm.value).subscribe((d) => {
          this.dialogRef.close(d);
        });
      } else {
        this.ventaService.nuevaVenta(this.ventaForm.value).subscribe((d) => {
          this.dialogRef.close(d);
        });
      }
    }
  }

  cerrar(): void {
    this.dialogRef.close();
  }
}
