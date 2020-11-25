import React from 'react'
import { Link } from 'react-router-dom'
import MentorForm from '../../../components/MentorForm'
import httpService from '../../../services/httpService'

const AddMentor = () => {

    const handleSubmit = (mentor) => {

        httpService.post('/mentor', mentor)
            .then(response => {
                alert('Success')
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <h1>Novo mentor</h1>

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
            <Link to="/mentor"><button type="button">Voltar para mentores</button></Link>
        </div>
    )
}

export default AddMentor