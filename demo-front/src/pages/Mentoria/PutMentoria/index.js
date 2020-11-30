import React, { useState, useEffect } from 'react'
import { useParams, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import MentoriaForm from '../../../components/Form/MentoriaForm'
import { Typography } from '@material-ui/core'

const PutMentoria = () => {
    let history = useHistory()
    const [mentoria, setMentoria] = useState({})
    const [isFinished, setIsFinished] = useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/mentoria/${id}`)
            .then(({ data }) => {
                setMentoria(data)
                setIsFinished(true)
            })
    }, [id])

    const handleSubmit = (mentoria) => {

        httpService.put(`/mentoria/${id}`, mentoria)
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
            <Typography variant="h1" color="primary">Alterar mentoria: {mentoria.alunoName} - {mentoria.mentorName}</Typography>

            {!isFinished && <p>Carregando</p>}

            {isFinished &&
                <MentoriaForm
                    initialValues={{
                        alunoId: mentoria.alunoId,
                        mentorId: mentoria.mentorId,
                    }}
                    handleSubmit={handleSubmit}
                />
                }
        </div>
    )
}
export default PutMentoria