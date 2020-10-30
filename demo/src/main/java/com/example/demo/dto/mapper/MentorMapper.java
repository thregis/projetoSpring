package com.example.demo.dto.mapper;

import com.example.demo.dto.MentorDTO;
import com.example.demo.model.Mentor;
import com.example.demo.model.Programa;

public class MentorMapper {

    public static MentorDTO toMentorDTO(Mentor mentor){
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(mentor.getId());
        mentorDTO.setName(mentor.getName());
        mentorDTO.setIdade(mentor.getIdade());
        mentorDTO.setPais(mentor.getPais());
        mentorDTO.setEscola(mentor.getEscola());
        if (mentor.getPrograma() == null){
            mentorDTO.setProgramaId(null);
            mentorDTO.setProgramaName(null);
        }else{
            mentorDTO.setProgramaId(mentor.getPrograma().getId());
            mentorDTO.setProgramaName(mentor.getPrograma().getName());
        }
        mentorDTO.setActive(mentor.getActive());
        return mentorDTO;
    }

    public static Mentor toMentor(MentorDTO mentorDTO){
        Mentor mentor = new Mentor();
        mentor.setId(mentorDTO.getId());
        mentor.setName(mentorDTO.getName());
        mentor.setIdade(mentorDTO.getIdade());
        mentor.setPais(mentorDTO.getPais());
        mentor.setEscola(mentorDTO.getEscola());
        if (mentorDTO.getProgramaId() == null){
            mentor.setPrograma(null);
        }else{
            Programa programa = new Programa();
            programa.setId((mentorDTO.getProgramaId()));
            mentor.setPrograma(programa);
        }
        mentor.setActive(mentorDTO.getActive());
        return mentor;
    }
}
