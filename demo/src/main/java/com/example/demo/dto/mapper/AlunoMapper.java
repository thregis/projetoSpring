package com.example.demo.dto.mapper;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
//@DecoratedWith(AlunoMapperDecorator.class)
public interface AlunoMapper {

    @Mappings({
            @Mapping(source = "programaId", target = "programa.id")})
    Aluno toAluno(AlunoDTO alunoDTO);

    @Mappings({
            @Mapping(source = "programa.id", target = "programaId"),
            @Mapping(source = "programa.name", target = "programaName")
    })
    AlunoDTO toAlunoDTO(Aluno aluno);

    //@Mapping(source="aluno.mentor.name", target = "mentorName")
//    public static AlunoDTO toAlunoDTO(Aluno aluno){
//        AlunoDTO alunoDTO = new AlunoDTO();
//        alunoDTO.setId(aluno.getId());
//        alunoDTO.setName(aluno.getName());
//        alunoDTO.setClasse(aluno.getClasse());
//        if (aluno.getPrograma() == null){
//            alunoDTO.setProgramaId(null);
//            alunoDTO.setProgramaName(null);
//        }else{
//            alunoDTO.setProgramaId(aluno.getPrograma().getId());
//            alunoDTO.setProgramaName(aluno.getPrograma().getName());
//        }
//        alunoDTO.setActive(aluno.getActive());
//        return alunoDTO;
//    }

//
//    public static Aluno toAluno(AlunoDTO alunoDTO){
//        Aluno aluno = new Aluno();
//        aluno.setId(alunoDTO.getId());
//        aluno.setName(alunoDTO.getName());
//        aluno.setClasse(alunoDTO.getClasse());
//        if (alunoDTO.getProgramaId() == null){
//            aluno.setPrograma(null);
//        }else{
//            Programa programa = new Programa();
//            programa.setId((alunoDTO.getProgramaId()));
//            aluno.setPrograma(programa);
//        }
//        aluno.setActive(alunoDTO.getActive());
//        return aluno;
//    }
}
