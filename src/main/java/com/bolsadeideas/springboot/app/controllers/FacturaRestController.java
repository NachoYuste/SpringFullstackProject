package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.view.xml.FacturaList;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {
    
    @Autowired 
    private IFacturaDao facturaDao;

    @GetMapping("/listar")
    public FacturaList listar(){
        return new FacturaList( (List<Factura>) facturaDao.findAll());
    }


}
