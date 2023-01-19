package com.bolsadeideas.springboot.app.view.json;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bolsadeideas.springboot.app.models.entity.Factura;

@Component
public class FacturaListJsonView extends MappingJackson2JsonView{

    @Override
    protected Object filterModel(Map<String, Object> model) {

        model.remove("titulo");
        model.remove("page");

        @SuppressWarnings("unchecked")
        List<Factura> facturas = (List<Factura>) model.get("facturas");
        model.remove("facturas");
        model.put("facturas", facturas);

        return super.filterModel(model);
    }
    
}
