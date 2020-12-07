import { FormControl } from '@material-ui/core'
import React, { useState } from 'react'
import { color } from '../../../models/programa'
import ButtonMentorHome from '../../Buttons/ButtonMentorHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectPrograma from '../../Select/SelectPrograma'


const MentorForm = ({ initialValues, handleSubmit }) => {
    const [mentor, setMentor] = useState(initialValues)

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
        if(mentor.name.length <3){
            alert('Campo de "Nome" requer ao menos três dígitos')
        }
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome"
                    id="mentor[name]"
                    name="name"
                    onChange={handleChange}
                    color={color(mentor.name)}
                    value={mentor.name}
                />

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
                <ButtonSubmit/>
                <ButtonMentorHome/>
            </FormControl>
        </form>
    )
}

export default MentorForm