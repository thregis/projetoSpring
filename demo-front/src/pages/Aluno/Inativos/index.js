import React from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'


class AlunoInativo extends React.Component{
    constructor(){
        super()

        this.state = {
            alunos: []
        }
        this.reactivateAluno = this.reactivateAluno.bind(this)

    }

    componentDidMount(){
        httpService.get('/aluno/reativacao')
        .then(({data}) => {
            console.log(data)

            this.setState({alunos: data})
        })
        .catch(error => {
            console.error(error)
        })
    }

    reactivateAluno = (id) => {
        console.log(id)
        httpService.post(`/reativacao/${id}`)
        .then(() => {
            const {alunos} = this.state

            const updatedAlunos = this.state.alunos.filter(
                aluno => aluno.id !== id
            )

            this.setState({
                alunos: updatedAlunos
            })
    }).catch(error => {
        console.error(error)
    })
}


    render(){
        const {alunos} = this.state
        
        return(
            <div>
                <h1>Alunos Inativos</h1>

                <table>
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Classe</th>
                        <th>Programa</th>
                        <th>Reativação</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.alunos.map(aluno =>
                            <tr key={aluno.id}>
                                <td>{aluno.name}</td>
                                <td>{aluno.classe}</td>
                                <td>{aluno.programaName}</td>
                                <td><button onClick={ () => this.reactivateAluno(aluno.id)}>Reativar aluno</button></td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
                <Link to="/aluno"><button type="button">Voltar para alunos ativos</button></Link>

            </div>
        )
    }
}

export default AlunoInativo