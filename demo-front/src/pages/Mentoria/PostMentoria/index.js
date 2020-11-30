import { Typography } from '@material-ui/core'
import React from 'react'
import { useHistory } from 'react-router-dom'
import MentoriaForm from '../../../components/Form/MentoriaForm'
import httpService from '../../../services/httpService'

const AddMentoria = () => {
    let history = useHistory()
    const handleSubmit = (mentoria) => {

        httpService.post('/mentoria', mentoria)
            .then(response => {
                alert('Success')
                history.push("/mentoria")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Nova mentoria</Typography>

            <MentoriaForm
                initialValues={{
                    alunoId: 0,
                    mentorId: 0,  
                }}
                handleSubmit={handleSubmit}
            />
        </div>
    )
}
export default AddMentoria