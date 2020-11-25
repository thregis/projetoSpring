import React, { useEffect, useState } from 'react'
import httpService from '../../../services/httpService'
import { Link, useParams } from 'react-router-dom'

const MentorById = () => {
    const [mentor, setMentor] = useState({})

    const { id } = useParams()

    useEffect(() => {
        httpService.get(`/mentor/${id}`)
            .then(({ data }) => {
                setMentor(data)
            })
    }, [id])

    const deleteMentor = (id) => {
        httpService.delete(`/mentor/${id}`)
            .then(response => {
                alert('Success')
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <h1>Análise de mentor</h1>
            {
                <ul>
                    <li>ID: {mentor.id}</li>
                    <li>Nome: {mentor.name}</li>
                    <li>Idade: {mentor.idade}</li>
                    <li>País: {mentor.pais}</li>
                    <li>Escola: {mentor.escola}</li>
                    <li>Programa: {mentor.programaName}</li>
                    <Link to={`/mentor/${mentor.id}/update`}><button type="button">Alterar mentor</button></Link>
                    <Link to={'/mentor'}><button onClick={() => deleteMentor(mentor.id)}>Remover mentor</button></Link>
                </ul>
            }
            <Link to="/mentor"><button type="button">Voltar para mentores</button></Link>
        </div>
    )
}

export default MentorById