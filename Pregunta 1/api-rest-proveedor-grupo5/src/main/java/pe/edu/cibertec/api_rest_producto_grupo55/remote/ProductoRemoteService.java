package pe.edu.cibertec.api_rest_producto_grupo55.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="api-rest-producto-grupo55")
public interface ProductoRemoteService {
	
	@GetMapping("/api-rest/v1/productoslistado")
	List<String> listadoproductos();

}
