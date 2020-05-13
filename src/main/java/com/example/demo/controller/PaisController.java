package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pais;
import com.example.demo.services.PaisService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaisController {

    @Autowired
    PaisService paisService;
    
     /* método que devuelve un objeto ResponseEntity 
     * de un subconjunto de la lista de paises (Page ).*/
    @GetMapping("/paises")
    public ResponseEntity<Page<Pais>> paginas
    (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String order,
            @RequestParam(defaultValue = "true") boolean asc
    )
    {
        Page<Pais> paises = paisService.paginas(PageRequest.of(page, size, Sort.by(order)));
        
        if(!asc)
            paises = paisService.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
        
        return new ResponseEntity<Page<Pais>>(paises, HttpStatus.OK);
        
    }
    
}

 /*En los parámetros del método hay 4 variables:
 page, que será la página que se va a devolver 
 (como el valor por defecto hemos puesto 0, será la primera); 
 size, que será el número de registros por página; order,
 que será el campo de la clase Pais a partir del cual se van a ordenar los registros
 (puede ser id, nombre, o capital);
 y asc, que indica si es ascendente (de menor a mayor) o descendente(de mayor a menor).*/

/* Para obtener el subconjunto utilizamos el servicio 
 * y le pasamos como parámetro un objeto que implementa la interfaz Pageable. 
 * En el constructor de dicho objeto le pasamos los parámetros page, size y order */

/*El orden por defecto es ascendente si no se indica lo contrario (if(!asc)) ;
 *  en ese caso, indicamos que sea descendente con el método descending():*/
