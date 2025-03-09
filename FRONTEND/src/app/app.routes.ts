import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { ProductoComponent } from './components/producto/producto.component';
import { VendedorComponent } from './components/vendedor/vendedor.component';
import { VentaComponent } from './components/venta/venta.component';
import { PruebaComponent } from './components/forms/prueba/prueba.component';

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
    path: 'prueba',
    component: PruebaComponent
},
{
    path: '**',
    redirectTo: ''
}];