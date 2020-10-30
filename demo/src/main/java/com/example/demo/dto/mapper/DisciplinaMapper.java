package com.example.demo.dto.mapper;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.model.Disciplina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {


    Disciplina toDisciplina(DisciplinaDTO disciplinaDTO);

    DisciplinaDTO toDisciplinaDTO(Disciplina disciplina);

}
