import { FormControl, FormHelperText, Typography } from '@material-ui/core'
import React from 'react'
import ButtonAvaliacaoHome from '../../Buttons/ButtonAvaliacaoHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectDisciplina from '../../Select/SelectDisciplina'
import SelectMentoria from '../../Select/SelectMentoria'

const AvaliacaoForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [avaliacao, setAvaliacao] = React.useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})

    const handleChange = (event) => {
        const { name, value } = event.target

        setAvaliacao({
            ...avaliacao,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(avaliacao)
        handleSubmit(avaliacao)
        setFormErrors(validate(avaliacao))
    }

    const validate = (avaliacao) => {
        let errors = {}
        if (!avaliacao.mentoriaId) {
            errors.mentoria = "Campo necessário."
        } else if (!avaliacao.disciplinaId) {
            errors.disciplina = "Campo necessário."
        } else if (!avaliacao.nota) {
            errors.nota = "Campo necessário."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
            <SelectMentoria
                    label="Mentoria*"
                    id="avaliacao[mentoriaId]"
                    name="mentoriaId"
                    onChange={handleChange}
                    value={avaliacao.mentoriaId}
                />
                {formErrors.mentoria && (<Typography color="secondary">{formErrors.mentoria}</Typography>)}

                <SelectDisciplina
                    label="Disciplina*"
                    id="avaliacao[disciplinaId]"
                    name="disciplinaId"
                    onChange={handleChange}
                    value={avaliacao.disciplinaId}
                />
                {formErrors.disciplina && (<Typography color="secondary">{formErrors.disciplina}</Typography>)}

                <Input
                    label="Nota*"
                    id="avaliacao[nota]"
                    name="nota"
                    type="number"
                    onChange={handleChange}
                    value={avaliacao.nota}
                    error={formErrors.nota && "true"}
                />
                <FormHelperText style={{ margin: 8 }}>Ex: 10,0</FormHelperText>
                {formErrors.nota && (<Typography color="secondary">{formErrors.nota}</Typography>)}


                <Input
                    label="Data"
                    id="avaliacao[data]"
                    name="data"
                    type="date"
                    //variant="standard"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={handleChange}
                    value={avaliacao.data}
                />


                <ButtonSubmit />
                <ButtonAvaliacaoHome/>

            </FormControl>
        </form>
    )
}

export default AvaliacaoForm