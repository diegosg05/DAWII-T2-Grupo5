package cibertec.edu.pe.api_security.controller;

import cibertec.edu.pe.api_security.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Inca Kola", 6.5, 40),
            new Producto(2, "Coca Cola", 3.5, 30),
            new Producto(3, "Fanta", 4.5, 18),
            new Producto(4, "Pepsi", 5.0, 16)
    ));

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {

        return ResponseEntity.ok(productos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoPorId(@PathVariable Integer id) {

        Optional<Producto> optionalProducto = productos
                .stream()
                .filter(producto -> producto
                        .getId().equals(id))
                .findFirst();

        return optionalProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Producto> guardarNuevoProducto(@RequestBody Producto producto) {

        producto.setId(productos.isEmpty() ? 1 : productos.size() + 1);
        productos.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);

    }

    @PutMapping
    public ResponseEntity<Void> actualizarProducto(@RequestBody Producto actualizarProducto) {

        Optional<Producto> optionalProducto = productos
                .stream()
                .filter(prod -> prod
                        .getId().equals(actualizarProducto.getId()))
                .findFirst();

        if (optionalProducto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var producto = optionalProducto.get();

        producto.setNombre(actualizarProducto.getNombre());
        producto.setPrecio(actualizarProducto.getPrecio());
        producto.setStock(actualizarProducto.getStock());

        return ResponseEntity.noContent().build();

    }
}
