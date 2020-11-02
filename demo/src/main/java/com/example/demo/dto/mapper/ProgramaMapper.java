package com.example.demo.dto.mapper;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Programa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ProgramaMapper {

    @Mappings({
            @Mapping(source = "mentores", target = "mentores")})
    Programa toPrograma(ProgramaDTO programaDTO);

    @Mappings({
            @Mapping(source = "mentores", target = "mentores")})
    ProgramaDTO toProgramaDTO(Programa programa);

//    public static ProgramaDTO toProgramaDTO(Programa programa){
//
//        ProgramaDTO programaDTO = new ProgramaDTO();
//        programaDTO.setId(programa.getId());
//        programaDTO.setName(programa.getName());
//        programaDTO.setDataInicio(programa.getDataInicio());
//        programaDTO.setDataFinal(programa.getDataFinal());
//        programaDTO.setActive(programa.getActive());
//        programaDTO.setMentores(mentoresToDTO(programa));
//        return programaDTO;
//    }
//
//    private static List<MentorDTO> mentoresToDTO(Programa programa) {
//        return programa.getMentores()
//                .stream()
//                .map(MentorMapper::toMentorDTO)
//                .filter(MentorDTO::getActive)
//                .collect(Collectors.toList());
//    }
//
//    public static Programa toPrograma(ProgramaDTO programaDTO){
//    Programa programa = new Programa();
//    programa.setId(programaDTO.getId());
//    programa.setName(programaDTO.getName());
//    programa.setDataInicio(programaDTO.getDataInicio());
//    programa.setDataFinal(programaDTO.getDataFinal());
//    programa.setActive(programaDTO.getActive());
//    programa.setMentores(DTOToMentor(programaDTO));
//    return programa;
//    }
//    private static List<Mentor> DTOToMentor(ProgramaDTO programaDTO) {
//        if(programaDTO.getMentores() ==null){
//            return Collections.emptyList();
//        }
//
//        return programaDTO.getMentores()
//                .stream()
//                .map(MentorMapper::toMentor)
//                .filter(Mentor::getActive)
//                .collect(Collectors.toList());
//    }
}
