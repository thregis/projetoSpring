import { Typography } from '@material-ui/core'
import React from 'react'
import { useHistory } from 'react-router'
import ProgramaForm from '../../../components/Form/ProgramaForm'
import httpService from '../../../services/httpService'

const AddPrograma = () => {
    let history = useHistory()
    const handleSubmit = (programa) => {
        httpService.post('/programa', programa)
            .then(response => {
                history.push('/programa')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Novo programa</Typography>

            <ProgramaForm
                initialValues={{
                    name: "",
                    dataInicio: "",
                    dataFinal: "",
                }}
                handleSubmit={handleSubmit}
            />
        </div>
    )
}
export default AddPrograma