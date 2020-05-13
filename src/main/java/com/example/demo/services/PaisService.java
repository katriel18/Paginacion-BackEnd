package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Pais;
import com.example.demo.repository.PaisRepository;

@Service
@Transactional// Realizar operaciones contra una base de datos
public class PaisService {

    @Autowired
    PaisRepository paisRepository;

    public Page<Pais> paginas(Pageable pageable){
        return paisRepository.findAll(pageable);
    }
    
    /*
     * El método principal devolverá una objeto tipo Page,
     * que no es más que un subconjunto de una lista.
     * Como parámetro lleva un objeto tipo Pageable, 
     * una interfaz que implementa diversas funcionalidades para acceder a la página 
     * (número de registros, número de página, ascendente o descendente, etc).
     */
}