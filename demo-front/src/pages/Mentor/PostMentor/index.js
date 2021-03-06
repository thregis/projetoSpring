import { Typography } from '@material-ui/core'
import React from 'react'
import { useHistory } from 'react-router-dom'
import MentorForm from '../../../components/Form/MentorForm'
import httpService from '../../../services/httpService'

const AddMentor = () => {
    let history = useHistory()
    const handleSubmit = (mentor) => {

        httpService.post('/mentor', mentor)
            .then(response => {
                alert('Success')
                history.push("/mentor")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Novo mentor</Typography>

            <MentorForm
                initialValues={{
                    name: "",
                    idade: 0,
                    pais: "",
                    escola: "",
                    programaId: 0,
                }}
                handleSubmit={handleSubmit}
            />
        </div>
    )
}

export default AddMentor