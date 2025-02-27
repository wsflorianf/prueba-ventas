import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { VentaService } from '../../../services/venta/venta.service';

@Component({
  selector: 'app-eliminar-venta',
  imports: [MatDialogModule, MatButtonModule],
  templateUrl: './eliminar-venta.component.html',
  styleUrl: './eliminar-venta.component.css'
})
export class EliminarVentaComponent {
  constructor(
    public dialogRef: MatDialogRef<EliminarVentaComponent>,
    private ventaService: VentaService,
    @Inject(MAT_DIALOG_DATA) public data: { mensaje: string, id: number }
  ) {}

  confirmar(): void {
    console.log(this.data.id)
    this.ventaService.eliminarVenta(this.data.id).subscribe(d=>{
      this.dialogRef.close(d);
    })
  }

  cancelar(): void {
    this.dialogRef.close(false);
  }
}
