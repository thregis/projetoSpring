import { FormControl, Typography } from '@material-ui/core'
import React from 'react'
import { color } from '../../../models/programa'
import ButtonDisciplinaHome from '../../Buttons/ButtonDisciplinaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'


const DisciplinaForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [disciplina, setDisciplina] = React.useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})

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
        setFormErrors(validate(disciplina))
    }

    const validate = (disciplina) => {
        let errors = {}
        if (!disciplina.name){
            errors.name = "Campo precisa ser preenchido."
        }else if(disciplina.name.length !== 0 && disciplina.name.length < 3){
            errors.name = "O campo deve ter pelo menos 3 caracteres."
        }else if (disciplina.name.length > 50){
            errors.name = "O campo deve ter no máximo 50 caracteres."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{minWidth: 120}}>
            <Input
                label="Nome"
                id="disciplina[name]"
                name="name"
                onChange={handleChange}
                error={formErrors.name && "true"}
                value={disciplina.name}
            />
            {formErrors.name && (<Typography color="secondary">{formErrors.name}</Typography>)}
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