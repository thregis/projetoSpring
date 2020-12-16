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

    const onBlur = (event) => {
        event.preventDefault()
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
        } else if (!mentor.pais){
            errors.pais = "Campo precisa ser preenchido."
        } else if (mentor.pais.length > 50){
            errors.pais = "O campo deve ter no máximo 50 caracteres."
        } else if (!mentor.escola){
            errors.escola = "Campo precisa ser preenchido."
        }else if (mentor.escola.length > 50){
            errors.escola = "O campo deve ter no máximo 50 caracteres."
        }else if (!mentor.programaId){
            errors.programa = "Campo precisa ser preenchido."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit} onBlur={onBlur}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome*"
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
                    label="País*"
                    id="mentor[pais]"
                    name="pais"
                    onChange={handleChange}
                    error={formErrors.pais && "true"}
                    value={mentor.pais}
                />
                {formErrors.pais && (<Typography color="secondary">{formErrors.pais}</Typography>)}


                <Input
                    label="Escola*"
                    id="mentor[escola]"
                    name="escola"
                    onChange={handleChange}
                    error={formErrors.escola && "true"}
                    value={mentor.escola}
                />
                {formErrors.escola && (<Typography color="secondary">{formErrors.escola}</Typography>)}

                <SelectPrograma
                    label="Programa*"
                    id="mentor[programaId]"
                    name="programaId"
                    onChange={handleChange}
                    value={mentor.programaId}
                />
                {formErrors.programa && (<Typography color="secondary">{formErrors.programa}</Typography>)}

                <ButtonSubmit />
                <ButtonMentorHome />
            </FormControl>
        </form>
    )
}

export default MentorForm