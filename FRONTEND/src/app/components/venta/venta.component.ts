import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Venta } from '../../types/apiTypes';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { VentaService } from '../../services/venta/venta.service';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { NuevaVentaComponent } from '../forms/nueva-venta/nueva-venta.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { EliminarVentaComponent } from '../forms/eliminar-venta/eliminar-venta.component';

@Component({
  selector: 'app-venta',
  imports: [MatTableModule, MatSortModule, MatButtonModule, MatIconModule],
  templateUrl: './venta.component.html',
  styleUrl: './venta.component.css',
})
export class VentaComponent implements OnInit, AfterViewInit {
  displayedColumns: String[] = [
    'id',
    'vendedor',
    'producto',
    'cantidad',
    'fecha',
    'acciones'
  ];
  dataSource = new MatTableDataSource();

  constructor(private service: VentaService, private dialog: MatDialog) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.service.getVentas().subscribe((data) => {
      this.dataSource.data = data;
    });
  }

  nuevaVenta(): void {
    const dialogRef = this.dialog.open(NuevaVentaComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.dataSource.data.push(result);
        this.dataSource.sort = this.sort;
      }
    });
  }

  editarVenta(venta: Venta): void {
    const dialogRef = this.dialog.open(NuevaVentaComponent, {
      width: '400px',
      data: venta // Enviamos los datos al diálogo
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const index = this.dataSource.data.findIndex((v: any) => v.id === venta.id);

        if (index !== -1) {
          this.dataSource.data[index] = result;
        }

        this.dataSource.sort = this.sort;        
      }
    });
  }

  confirmarEliminarVenta(venta: Venta): void{
    const dialogRef = this.dialog.open(EliminarVentaComponent, {
      width: '300px',
      data: { mensaje: `¿Estás seguro de que quieres eliminar la venta ${venta.id}?`, id: venta.id }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        const index = this.dataSource.data.findIndex((v: any) => v.id === venta.id);
        
        if (index !== -1) {
          this.dataSource.data.splice(index, 1)
        }
  
        this.dataSource.sort = this.sort;        
      }


    });
  }
}
