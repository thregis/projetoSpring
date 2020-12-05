import { FormControl } from '@material-ui/core'
import React from 'react'
import ButtonProgramaHome from '../../Buttons/ButtonProgramaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'

const ProgramaForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [programa, setPrograma] = React.useState(initialValues)

    const handleChange = (event) => {
        const { name, value } = event.target

        setPrograma({
            ...programa,
            [name]: value
        })
    }

    const onSubmit = (event) => {
        event.preventDefault()
        console.log(programa)
        handleSubmit(programa)
    }

    return (
        <form onSubmit={onSubmit}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome"
                    id="programa[name]"
                    name="name"
                    onChange={handleChange}
                    value={programa.name}
                />

                <Input
                    label="Data de início"
                    id="programa[dataInicio]"
                    name="dataInicio"
                    onChange={handleChange}
                    value={programa.dataInicio}
                />

                <Input
                    label="Data de término"
                    id="programa[dataFinal]"
                    name="dataFinal"
                    onChange={handleChange}
                    value={programa.dataFinal}
                />


                <ButtonSubmit />
                <ButtonProgramaHome />

            </FormControl>
        </form>
    )
}

export default ProgramaForm