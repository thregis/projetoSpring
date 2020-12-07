import { FormControl } from '@material-ui/core'
import React from 'react'
import { color } from '../../../models/programa'
import ButtonDisciplinaHome from '../../Buttons/ButtonDisciplinaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'


const DisciplinaForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [disciplina, setDisciplina] = React.useState(initialValues)

    const handleChange = (event) => {
        const { name, value } = event.target

        setDisciplina({
            ...disciplina,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(disciplina)
        handleSubmit(disciplina)
        if(disciplina.name.length <3){
            alert('Campo de "Nome" requer ao menos três dígitos')
        }
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{minWidth: 120}}>
            <Input
                label="Nome"
                id="disciplina[name]"
                name="name"
                onChange={handleChange}
                color={color(disciplina.name)}
                value={disciplina.name}
            />

            <Input
                label="Descrição"
                id="disciplina[descricao]"
                name="descricao"
                onChange={handleChange}
                value={disciplina.descricao}
            />

                <ButtonSubmit />
                <ButtonDisciplinaHome/>
                
                </FormControl>
                </form>
    )}

    export default DisciplinaForm