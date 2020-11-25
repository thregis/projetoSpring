import React, { useState, useEffect } from 'react'
import { useParams, Link } from 'react-router-dom'
import httpService from '../../../services/httpService'
import MentorForm from '../../../components/MentorForm'

const PutMentor = () => {
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
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <h1>Alterar mentor: {mentor.name}</h1>

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
                <Link to="/mentor"><button type="button">Voltar para mentores</button></Link>
        </div>
    )
}

export default PutMentor