//package com.example.demo.dto.mapper;
//
//import com.example.demo.dto.AlunoDTO;
//import com.example.demo.model.Aluno;
//import org.mapstruct.DecoratedWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AlunoMapperDecorator implements AlunoMapper {
//
//    @Autowired
//    @Qualifier("delegate")
//    private AlunoMapper delegate;
//
//
//    @Override
//    public Aluno toAluno(AlunoDTO alunoDTO){
//        Aluno aluno = delegate.toAluno(alunoDTO);
//        return aluno;
//    }
//
//    @Override
//    public AlunoDTO toDTO(Aluno aluno){
//        AlunoDTO alunoDTO = delegate.toDTO(aluno);
//        Mentor mentor = aluno.getMentor();
//        alunoDTO.setMentorName(mentor.getName());
// alunoDto.setMentorName(mentor.getName().concat(" - ").concat(mentor.getPais())); por exemplo
//        return alunoDTO;
//    }
//}
