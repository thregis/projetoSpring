import { FormControl } from '@material-ui/core'
import React from 'react'
import ButtonAvaliacaoHome from '../../Buttons/ButtonAvaliacaoHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'
import SelectDisciplina from '../../Select/SelectDisciplina'
import SelectMentoria from '../../Select/SelectMentoria'

const AvaliacaoForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [avaliacao, setAvaliacao] = React.useState(initialValues)

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
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
            <SelectMentoria
                    label="Mentoria"
                    id="avaliacao[mentoriaId]"
                    name="mentoriaId"
                    onChange={handleChange}
                    value={avaliacao.mentoriaId}
                />
                <SelectDisciplina
                    label="Disciplina"
                    id="avaliacao[disciplinaId]"
                    name="disciplinaId"
                    onChange={handleChange}
                    value={avaliacao.disciplinaId}
                />
                <Input
                    label="Nota"
                    id="avaliacao[nota]"
                    name="nota"
                    type="number"
                    onChange={handleChange}
                    value={avaliacao.nota}
                />

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