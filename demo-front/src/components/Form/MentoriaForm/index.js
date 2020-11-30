import { FormControl } from '@material-ui/core'
import React, { useState } from 'react'
import ButtonMentoriaHome from '../../Buttons/ButtonMentoriaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectAluno from '../../Select/SelectAluno'
import SelectMentor from '../../Select/SelectMentor'

const MentoriaForm = ({ initialValues, handleSubmit }) => {
    const [mentoria, setMentoria] = useState(initialValues)

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
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <SelectAluno
                    label="Aluno"
                    id="mentoria[alunoId]"
                    name="alunoId"
                    onChange={handleChange}
                    value={mentoria.alunoId}
                />
                <SelectMentor
                    label="Mentor"
                    id="mentoria[mentorId]"
                    name="mentorId"
                    onChange={handleChange}
                    value={mentoria.mentorId}
                />
                <ButtonSubmit />
                <ButtonMentoriaHome />
            </FormControl>
        </form>
    )
}
export default MentoriaForm