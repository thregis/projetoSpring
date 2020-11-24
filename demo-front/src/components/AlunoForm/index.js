import React from 'react'
import Input from '../Input'


const AlunoForm = ({initialValues, handleSubmit}) => {
    const [aluno, setAluno] = React.useState(initialValues)

    const handleChange = (event) => {
        const { input, value } = event.target

        setAluno({
            ...aluno,
            [input]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()

        handleSubmit(aluno)
    }

    return(
                <form onSubmit={onSubmit}>
                    <Input
                        label="Name"
                        id="aluno[name]"
                        name="name"
                        onChange={handleChange}
                        value={aluno.name}
                    />

                    <Input
                        label="Classe"
                        id="aluno[classe]"
                        name="classe"
                        onChange={handleChange}
                        value={aluno.classe}
                    />

                    <Input
                        label="Programa"
                        id="aluno[programaId]"
                        name="programaId"
                        onChange={handleChange}
                        value={aluno.programaId}
                        type="number"
                    />
                    <input type="submit" value="Adicionar aluno" />
                </form>
    )}

export default AlunoForm