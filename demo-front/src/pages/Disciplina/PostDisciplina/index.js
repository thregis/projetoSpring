import { Typography } from '@material-ui/core'
import React from 'react'
import { useHistory } from 'react-router'
import DisciplinaForm from '../../../components/Form/DisciplinaForm'
import httpService from '../../../services/httpService'

const AddDisciplina = () => {
    let history = useHistory()
    const handleSubmit = (disciplina) => {
        httpService.post('/disciplina', disciplina)
            .then(response => {
                history.push('/disciplina')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Nova disciplina</Typography>

            <DisciplinaForm
                initialValues={{
                    name: "",
                    descricao: "",
                }}
                handleSubmit={handleSubmit}
            />
        </div>
    )
}

export default AddDisciplina