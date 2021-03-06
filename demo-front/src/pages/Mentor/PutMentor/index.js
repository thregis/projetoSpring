import React, { useState, useEffect } from 'react'
import { useParams, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import MentorForm from '../../../components/Form/MentorForm'
import { Typography } from '@material-ui/core'

const PutMentor = () => {
    let history = useHistory()
    const [mentor, setMentor] = useState({})
    const [isFinished, setIsFinished] = useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/mentor/${id}`)
            .then(({ data }) => {
                setMentor(data)
                setIsFinished(true)
            })
    }, [id])

    const handleSubmit = (mentor) => {

        httpService.put(`/mentor/${id}`, mentor)
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
            <Typography variant="h1" color="primary">Alterar mentor: {mentor.name}</Typography>

            {!isFinished && <p>Carregando</p>}

            {isFinished &&
                <MentorForm
                    initialValues={{
                        name: mentor.name,
                        idade: mentor.idade,
                        pais: mentor.pais,
                        escola: mentor.escola,
                        programaId: mentor.programaId,
                    }}
                    handleSubmit={handleSubmit}
                />
                }
        </div>
    )
}

export default PutMentor