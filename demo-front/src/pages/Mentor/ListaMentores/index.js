import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'

const Mentor = () => {
    const [mentores, setMentores] = useState([])

    useEffect(() => {
        httpService.get('/mentor')
            .then(({ data }) => {
                setMentores(data)
            })
            .catch(error => {
                console.error(error)
            })

    }, [])

        return (
            <div>
                <h1>Mentores ativos</h1>

                <table>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Idade</th>
                            <th>País</th>
                            <th>Escola</th>
                            <th>Programa</th>
                            <th>Ver mentor</th>
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
                                <td><Link to={`mentor/${mentor.id}`}><button type="button">Inspecionar</button></Link></td>
                            </tr>
                        )}
                    </tbody>
                </table>
                <Link to="/mentor/reativacao"><button type="button">Mentores inativos</button></Link>
                <Link to="/mentor/add"><button type="button">Novo mentor</button></Link>
            </div>
        )

}

export default Mentor