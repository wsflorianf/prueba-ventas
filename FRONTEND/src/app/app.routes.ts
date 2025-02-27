import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { ProductoComponent } from './components/producto/producto.component';
import { NgModule } from '@angular/core';
import { VendedorComponent } from './components/vendedor/vendedor.component';
import { VentaComponent } from './components/venta/venta.component';

export const routes: Routes = [{
    path: '',
    component: InicioComponent
},
{
    path: 'productos',
    component: ProductoComponent
},
{
    path: 'vendedores',
    component: VendedorComponent
},
{
    path: 'ventas',
    component: VentaComponent
},
{
    path: '**',
    redirectTo: ''
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}