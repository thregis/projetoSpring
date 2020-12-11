import { FormControl, Typography } from '@material-ui/core'
import React, { useState } from 'react'
import ButtonMentoriaHome from '../../Buttons/ButtonMentoriaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectAluno from '../../Select/SelectAluno'
import SelectMentor from '../../Select/SelectMentor'

const MentoriaForm = ({ initialValues, handleSubmit }) => {
    const [mentoria, setMentoria] = useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})

    const handleChange = (event) => {
        const { name, value } = event.target

        setMentoria({
            ...mentoria,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(mentoria)
        handleSubmit(mentoria)
        setFormErrors(validate(mentoria))
    }

    const validate = (mentoria) => {
        let errors = {}
        if (!mentoria.alunoId){
            errors.aluno = "Os dois campos precisam ser preenchidos."
        }else if (!mentoria.mentorId){
            errors.mentor = "Os dois campos precisam ser preenchidos."
        }
        return errors
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <SelectAluno
                    label="Aluno*"
                    id="mentoria[alunoId]"
                    name="alunoId"
                    onChange={handleChange}
                    value={mentoria.alunoId}

                />
                <SelectMentor
                    label="Mentor*"
                    id="mentoria[mentorId]"
                    name="mentorId"
                    onChange={handleChange}
                    value={mentoria.mentorId}
                />
                <ButtonSubmit />
                <ButtonMentoriaHome />
            </FormControl>
                {(formErrors.aluno || formErrors.mentor) && (<Typography color="secondary">Os dois campos precisam ser preenchidos.</Typography>)}
        </form>
    )
}
export default MentoriaForm