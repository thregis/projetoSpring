import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'

const MentorInativo = () => {
    const [mentores, setMentores] = useState([])

    useEffect(() => {
        httpService.get("/mentor/reativacao")
            .then(({ data }) => {
                setMentores(data)
            })
            .catch(error => {
                console.error(error)
            })
    }, [])

    const reactivateMentor = (id) => {
        console.log(id)
        httpService.post(`/mentor/reativacao/${id}`)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <h1>Mentores inativos</h1>

            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Idade</th>
                        <th>País</th>
                        <th>Escola</th>
                        <th>Programa</th>
                        <th>Reativação</th>
                    </tr>
                </thead>
                <tbody>
                    {mentores.map(mentor =>
                        <tr key={mentor.id}>
                            <td>{mentor.name}</td>
                            <td>{mentor.idade}</td>
                            <td>{mentor.pais}</td>
                            <td>{mentor.escola}</td>
                            <td>{mentor.programaId}</td>
                            <td><button type="button" onClick={() => reactivateMentor(mentor.id)}>Reativar</button></td>
                        </tr>
                    )}
                </tbody>
            </table>
            <Link to="/mentor"><button type="button">Voltar para mentores ativos</button></Link>
        </div>
    )
}

export default MentorInativo