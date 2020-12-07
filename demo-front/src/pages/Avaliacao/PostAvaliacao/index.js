import { Typography } from '@material-ui/core'
import React from 'react'
import { useHistory } from 'react-router'
import AvaliacaoForm from '../../../components/Form/AvaliacaoForm'
import httpService from '../../../services/httpService'

const AddAvaliacao = () => {
    let history = useHistory()
    const handleSubmit = (avaliacao) => {
        httpService.post('/avaliacao', avaliacao)
            .then(response => {
                history.push('/avaliacao')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Nova avaliação</Typography>

            <AvaliacaoForm
                initialValues={{
                    mentoriaId: 0,
                    disciplinaId: 0,
                    nota: "",
                    data: "",
                }}
                handleSubmit={handleSubmit}
            />
        </div>
    )
}

export default AddAvaliacao