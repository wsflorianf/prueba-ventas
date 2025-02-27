import { CurrencyPipe } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Vendedor } from '../../types/apiTypes';
import { VendedorService } from '../../services/vendedor/vendedor.service';
import { MatSort, MatSortModule } from '@angular/material/sort';

@Component({
  selector: 'app-vendedor',
  imports: [MatTableModule, MatSortModule],
  templateUrl: './vendedor.component.html',
  styleUrl: './vendedor.component.css',
})
export class VendedorComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'nombre', 'email'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  constructor(private service: VendedorService) {}

  ngOnInit(): void {
    this.service.getVendedores().subscribe((data) => {
      this.dataSource.data = data;
    });
  }
}
