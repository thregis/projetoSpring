package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.service.MentorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Api de Mentor")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/mentor")
public class MentorController {

    // @Autowired
    //MentorRepository mentorRepository;

    @Autowired
    MentorService mentorService;

    @ApiOperation(value="Página de mentores ativos", notes = "Retorna uma página contendo mentores ativos.")
    @GetMapping
    public ResponseEntity<Page<MentorDTO>> getMentores(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(mentorService.getMentores(pageable).get());
    }

    @ApiOperation(value = "Lista de mentores ativos", notes = "Retorna uma lista com todos os mentores ativos. Utilizado para select no front.")
    @GetMapping("/lista")
    public ResponseEntity<List<MentorDTO>> getMentoresList(){
        return ResponseEntity.ok(mentorService.getMentoresList().get());
    }

    @ApiOperation(value="Página de mentores inativos", notes = "Retorna uma página contendo mentores inativos.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<MentorDTO>> getMentoresInativos(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(mentorService.getMentoresInativos(pageable).get());
    }

    @ApiOperation(value="Busca de mentor por ID", notes = "Retorna o mentor com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> getMentor(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(mentorService.getMentorByIndex(id).get());
    }

    @ApiOperation(value="Criação de mentor", notes = "Permite a criação de mentor.")
    @PostMapping
    public ResponseEntity<MentorDTO> postMentor(@RequestBody @Validated MentorDTO mentorDTO) throws Exception {
        mentorService.criaMentor(mentorDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Remoção lógica de mentor", notes = "Altera o atributo active do mentor selecionado para false. Se houver mentoria associada, é também removida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable Long id) {
            mentorService.removeMentorByIndex(id);  //COM A ANOTAÇÃO NÃO PRECISA TRATAR PORQUE SE DER A EXCEÇÃO ELE PEGA DIRETO DE LÁ.
            return  ResponseEntity.ok().build();

//        try {           // SEM ANOTAÇÃO DE RESPONSESTATUS NA EXCEÇÃO, OU SE QUISER PASSAR A MENSAGEM NO BODY DA RESPOSTA
//            mentorService.removeMentorByIndex(id);
//            return ResponseEntity.ok().build();
//
//        } catch (NotFoundException ex) {
//            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
    }

    @ApiOperation(value="Edição de mentor", notes = "Altera as informações do mentor com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<MentorDTO> updateMentor(@PathVariable Long id, @RequestBody @Validated MentorDTO mentorDTO) {
        mentorService.alteraMentor(id, mentorDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Reativação lógica de mentor", notes = "Reativa o mentor do ID selecionado, alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<MentorDTO> reativaMentor(@PathVariable Long id) {
        mentorService.reativaMentor(id);
        return ResponseEntity.ok().build();
    }
}
