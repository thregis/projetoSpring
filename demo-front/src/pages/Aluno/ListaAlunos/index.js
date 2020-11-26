import React, { useEffect, useState } from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'
import { Button, Typography } from '@material-ui/core'
import ButtonInspect from '../../../components/Buttons/ButtonInspect';
import ButtonPersonAdd from '../../../components/Buttons/ButtonPersonAdd'


const Aluno = () => {
    const [alunos, setAlunos] = useState([])

    useEffect(() => {
        httpService.get('/aluno')
            .then(({ data }) => {
                setAlunos(data)
            })
            .catch(error => {
                console.error(error)
            })

    })

    return (
        <div>
            <Typography variant="h1" color="primary">Alunos ativos</Typography>

            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Classe</th>
                        <th>Programa</th>
                        <th>Ver aluno</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        alunos.map(aluno =>
                            <tr key={aluno.id}>
                                <td>{aluno.name}</td>
                                <td>{aluno.classe}</td>
                                <td>{aluno.programaName}</td>
                                <td><Link to={`/aluno/${aluno.id}`}>
                                    <ButtonInspect />
                                </Link></td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
            <Link to="/aluno/reativacao"><Button variant="contained" color="primary">Alunos inativos</Button></Link>
            <Link to="/aluno/add"><ButtonPersonAdd>Novo aluno</ButtonPersonAdd></Link>
        </div>
    )
}


export default Aluno