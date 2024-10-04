package com.FPV.pensiones.services;

import com.FPV.pensiones.model.DTO.ProductoDTO;
import com.FPV.pensiones.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Long id);
    Producto saveProducto(ProductoDTO productoDTO);
    Producto updateProducto(Long id, ProductoDTO productoDTO);
    Producto deleteProducto(Long id);
}
