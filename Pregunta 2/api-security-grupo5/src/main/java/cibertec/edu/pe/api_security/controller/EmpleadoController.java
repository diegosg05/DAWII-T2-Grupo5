package cibertec.edu.pe.api_security.controller;

import cibertec.edu.pe.api_security.model.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    private final List<Empleado> empleados = new ArrayList<>(Arrays.asList(
            new Empleado(1, "Diego"),
            new Empleado(2, "Kenny"),
            new Empleado(3, "Brian")
    ));

    @PostMapping
    public ResponseEntity<Empleado> saveEmpleado(@RequestBody Empleado empleado) {

        empleado.setId(empleados.isEmpty() ? 1 : empleados.size() + 1);
        empleados.add(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleado);

    }


}
