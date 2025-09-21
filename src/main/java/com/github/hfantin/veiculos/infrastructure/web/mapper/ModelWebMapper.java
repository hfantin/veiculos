package com.github.hfantin.veiculos.infrastructure.web.mapper;


import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.infrastructure.web.dto.ModelRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.ModelResponse;
import org.springframework.stereotype.Component;

@Component
public class ModelWebMapper {

    public Model toDomain(ModelRequest request) {
        Model model = new Model();
        model.setBrandId(request.getBrandId());
        model.setName(request.getName());
        return model;
    }

    public ModelResponse toResponse(Model model) {
        return new ModelResponse(
                model.getId(),
                model.getBrandId(),
                model.getName(),
                model.getCreatedAt()
        );
    }
}
