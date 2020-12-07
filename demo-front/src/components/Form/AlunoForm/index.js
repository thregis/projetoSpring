import { FormControl } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import { color } from '../../../models/programa'
import ButtonAlunoHome from '../../Buttons/ButtonAlunoHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectPrograma from '../../Select/SelectPrograma'


const AlunoForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [aluno, setAluno] = React.useState(initialValues)

    const handleChange = (event) => {
        const { name, value } = event.target

        setAluno({
            ...aluno,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(aluno)
        handleSubmit(aluno)
        if(aluno.name.length <3){
            alert('Campo de "Nome" requer ao menos três dígitos')
        }
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{minWidth: 120}}>
            <Input
                label="Nome"
                id="aluno[name]"
                name="name"
                onChange={handleChange}
                color={color(aluno.name)}
                value={aluno.name}
            />

            <Input
                label="Classe"
                id="aluno[classe]"
                name="classe"
                onChange={handleChange}
                value={aluno.classe}
            />

            <SelectPrograma
                label="Programa"
                id="aluno[programaId]"
                name="programaId"
                onChange={handleChange}
                value={aluno.programaId}
            />
                <ButtonSubmit />
                <ButtonAlunoHome/>
                
                </FormControl>
                </form>
    )}

export default AlunoForm