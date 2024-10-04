package com.FPV.pensiones.services;


import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.DTO.ProductoDTO;
import com.FPV.pensiones.model.Producto;
import com.FPV.pensiones.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RequestException("Producto no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Producto saveProducto(ProductoDTO productoDTO) {
        Producto producto = Producto.builder()
                .nombre(capitalizeFirstLetter(productoDTO.getNombre()))
                .tipoProducto(capitalizeFirstLetter(productoDTO.getTipoProducto()))
                .montoMinimo(productoDTO.getMontoMinimo())
                .categoria(productoDTO.getCategoria())
                .build();
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id, ProductoDTO productoDTO) {
        Producto existingProducto = getProductoById(id);
        Producto updatedProducto = Producto.builder()
                .id(existingProducto.getId())
                .nombre(capitalizeFirstLetter(productoDTO.getNombre()))
                .tipoProducto(capitalizeFirstLetter(productoDTO.getTipoProducto()))
                .montoMinimo(productoDTO.getMontoMinimo())
                .categoria(productoDTO.getCategoria())
                .build();
        return productoRepository.save(updatedProducto);
    }

    @Override
    public Producto deleteProducto(Long id) {
        Producto producto = getProductoById(id);
        productoRepository.delete(producto);
        return producto;
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
