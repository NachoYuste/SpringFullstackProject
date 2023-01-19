package com.bolsadeideas.springboot.app.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import com.bolsadeideas.springboot.app.models.entity.Factura;

public class  FacturaList {

    @XmlElement(name="facturas")
    public List<Factura> facturas;
    
    public FacturaList(){}

    public FacturaList(List<Factura> facturas){
        this.facturas = facturas;
    }

    public List<Factura> getFacturas(){
        return facturas;
    }
    
}
