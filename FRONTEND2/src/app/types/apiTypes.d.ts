export interface Venta {
    id:       number;
    vendedor: Vendedor;
    producto: Producto;
    cantidad: number;
    fecha:    Date;
}

export interface NuevaVenta{
    vendedorId: number;
    productoId: number;
    cantidad: number;
    fecha:    Date;
}

export interface Producto {
    id:     number;
    nombre: string;
    precio: number;
    stock:  number;
}

export interface Vendedor {
    id:     number;
    nombre: string;
    email:  string;
}
