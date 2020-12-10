import { FormControl, Typography } from '@material-ui/core'
import React, { useState } from 'react'
import ButtonMentorHome from '../../Buttons/ButtonMentorHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectPrograma from '../../Select/SelectPrograma'


const MentorForm = ({ initialValues, handleSubmit }) => {
    const [mentor, setMentor] = useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})

    const handleChange = (event) => {
        const { name, value } = event.target

        setMentor({
            ...mentor,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(mentor)
        handleSubmit(mentor)
        setFormErrors(validate(mentor))
    }

    const validate = (mentor) => {
        let errors = {}
        if (!mentor.name) {
            errors.name = "Campo precisa ser preenchido."
        } else if (mentor.name.length !== 0 && mentor.name.length < 3) {
            errors.name = "O campo deve ter pelo menos 3 caracteres."
        } else if (mentor.name.length > 50) {
            errors.name = "O campo deve ter no máximo 50 caracteres."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome"
                    id="mentor[name]"
                    name="name"
                    onChange={handleChange}
                    error={formErrors.name && "true"}
                    value={mentor.name}
                />
                {formErrors.name && (<Typography color="secondary">{formErrors.name}</Typography>)}

                <Input
                    label="Idade"
                    id="mentor[idade]"
                    name="idade"
                    onChange={handleChange}
                    value={mentor.idade}
                    type="number"
                />

                <Input
                    label="País"
                    id="mentor[pais]"
                    name="pais"
                    onChange={handleChange}
                    value={mentor.pais}
                />

                <Input
                    label="Escola"
                    id="mentor[escola]"
                    name="escola"
                    onChange={handleChange}
                    value={mentor.escola}
                />

                <SelectPrograma
                    label="Programa"
                    id="mentor[programaId]"
                    name="programaId"
                    onChange={handleChange}
                    value={mentor.programaId}
                />
                <ButtonSubmit />
                <ButtonMentorHome />
            </FormControl>
        </form>
    )
}

export default MentorForm