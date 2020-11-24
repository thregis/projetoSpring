import React from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'


class Aluno extends React.Component {
    constructor() {
        super()

        this.state = {
            alunos: []
        }
    }

    componentDidMount() {
        httpService.get('/aluno')
            .then(({ data }) => {
                console.log(data)

                this.setState({ alunos: data })
            })
            .catch(error => {
                console.error(error)
            })
    }

    render() {
        return (
            <div>
                <h1>Alunos ativos</h1>

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
                        this.state.alunos.map(aluno =>
                            <tr key={aluno.id}>
                                <td>{aluno.name}</td>
                                <td>{aluno.classe}</td>
                                <td>{aluno.programaName}</td>
                                <td><Link to={`/aluno/${aluno.id}`}>
                                    <button type="button">Inspecionar</button>
                                    </Link></td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
                <Link to="/aluno/reativacao"><button type="button">Alunos inativos</button></Link>
                <Link to="/aluno/add"><button type="button">Novo aluno</button></Link>
            </div>
        )
    }
}

export default Aluno