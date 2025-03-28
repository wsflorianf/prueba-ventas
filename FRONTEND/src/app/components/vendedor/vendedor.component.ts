import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Vendedor } from '../../types/apiTypes';
import { VendedorService } from '../../services/vendedor/vendedor.service';
import { MatSort, MatSortModule, Sort } from '@angular/material/sort';

@Component({
  selector: 'app-vendedor',
  imports: [MatTableModule, MatSortModule],
  templateUrl: './vendedor.component.html',
  styleUrl: './vendedor.component.css',
})
export class VendedorComponent implements OnInit {
  displayedColumns: string[] = ['nombre', 'email'];
  dataSource = new MatTableDataSource<Vendedor>([]);
  vendedores: Vendedor[] = [];
  columnasVisibles: string[] = ['nombre', 'email']; // Columnas que realmente se mostrarán

  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  constructor(private service: VendedorService) {}

  ngOnInit(): void {
    this.service.getVendedores().subscribe((data) => {
      this.dataSource.data = data
      this.dataSource.data.forEach((vendedor, index) => {
        vendedor.nombre = vendedor.nombre.toUpperCase()+"_"+(index+1);
      })
      
      // Configurar función de ordenamiento personalizada
      this.dataSource.sortingDataAccessor = (item: Vendedor, property: string) => {
        switch(property) {
          case 'nombre': return item.id;
          default: return item[property as keyof Vendedor];
        }
      };
    },undefined,()=>{
      this.dataSource.sort = this.sort;
    });
  }
}
