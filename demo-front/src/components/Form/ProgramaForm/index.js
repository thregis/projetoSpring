import { FormControl, FormHelperText, Typography } from '@material-ui/core'
import React from 'react'
import ButtonProgramaHome from '../../Buttons/ButtonProgramaHome'
import ButtonSubmit from '../../Buttons/ButtonSubmit'
import Input from '../../Input'

const ProgramaForm = ({ initialValues, handleSubmit }) => {
    console.log(initialValues)
    const [programa, setPrograma] = React.useState(initialValues)
    const [formErrors, setFormErrors] = React.useState({})

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
        setFormErrors(validate(programa))
    }

    const onBlur = (event) => {
        event.preventDefault()
        setFormErrors(validate(programa))
    }

    const validate = (programa) => {
        let errors = {}
        if (!programa.name){
            errors.name = "Campo precisa ser preenchido."
        }else if(programa.name.length !== 0 && programa.name.length < 3){
            errors.name = "O campo deve ter pelo menos 3 caracteres."
        }else if (programa.name.length > 50){
            errors.name = "O campo deve ter no máximo 50 caracteres."
        }
        return errors
    }


    return (
        <form onSubmit={onSubmit} onBlur={onBlur}>
            <FormControl style={{ minWidth: 120 }}>
                <Input
                    label="Nome*"
                    id="programa[name]"
                    name="name"
                    onChange={handleChange}
                    value={programa.name}
                    error={formErrors.name && "true"}
                    />
                    {formErrors.name && (<Typography color="secondary">{formErrors.name}</Typography>)}
                <Input
                    label="Data de início"
                    id="programa[dataInicio]"
                    name="dataInicio"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={handleChange}
                    value={programa.dataInicio}
                />
                <FormHelperText style={{ margin: 8 }}>Requer uma data passada</FormHelperText>

                <Input
                    label="Data de término"
                    id="programa[dataFinal]"
                    name="dataFinal"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
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