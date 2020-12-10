import { FormControl, Typography } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import ButtonAlunoHome from '../../Buttons/ButtonAlunoHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectPrograma from '../../Select/SelectPrograma'


const AlunoForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [aluno, setAluno] = React.useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})


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
        setFormErrors(validate(aluno))
    }

    const validate = (aluno) => {
        let errors = {}
        if (!aluno.name) {
            errors.name = "Campo precisa ser preenchido."
        } else if (aluno.name.length !== 0 && aluno.name.length < 3) {
            errors.name = "O campo deve ter pelo menos 3 caracteres."
        } else if (aluno.name.length > 50) {
            errors.name = "O campo deve ter no máximo 50 caracteres."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome"
                    id="aluno[name]"
                    name="name"
                    onChange={handleChange}
                    error={formErrors.name && "true"}
                    value={aluno.name}
                />
                {formErrors.name && (<Typography color="secondary">{formErrors.name}</Typography>)}

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
                <ButtonAlunoHome />

            </FormControl>
        </form>
    )
}

export default AlunoForm