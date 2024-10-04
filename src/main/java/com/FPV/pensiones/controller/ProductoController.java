package com.FPV.pensiones.controller;

import com.FPV.pensiones.model.DTO.ProductoDTO;
import com.FPV.pensiones.model.Producto;
import com.FPV.pensiones.services.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Producto", description = "Producto API endpoints")
public class ProductoController {
    private final ProductoService productoService;

    //Api Get all productos: http://localhost:8080/api/v1/productos
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        if(productos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Api Get producto by id: http://localhost:8080/api/v1/productos/1
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.FOUND);
    }

    //Api Add producto: http://localhost:8080/api/v1/productos
    @PostMapping("/productos")
    public ResponseEntity<Producto> saveProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.saveProducto(productoDTO), HttpStatus.CREATED);
    }

    //Api Update producto: http://localhost:8080/api/v1/productos/1
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.updateProducto(id, productoDTO), HttpStatus.OK);
    }

    //Api Delete producto: http://localhost:8080/api/v1/productos/1
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.deleteProducto(id), HttpStatus.NO_CONTENT);
    }
}
