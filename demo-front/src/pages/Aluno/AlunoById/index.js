import React from 'react'
import httpService from '../../../services/httpService'
import {
    Link,
    withRouter,
    //useHistory
  } from "react-router-dom"


class AlunoById extends React.Component {
    constructor() {
        super()

        this.state = {
            aluno: []         //useState
        }
        this.deleteAluno = this.deleteAluno.bind(this)
    }
    
    componentDidMount() {
        const {id} = this.props.match.params
        httpService.get(`/aluno/${id}`)
        .then(({ data }) => {
            console.log(data)
            
            this.setState({ aluno: data })
        })
        .catch(error => {
            console.error(error)
        })
    }

    deleteAluno = (id) => {
        httpService.delete(`/aluno/${id}`)
        .then(response => {
            alert('Success')
            /*let history = useHistory()
            history.push("/aluno")            se fosse hook */
        
        })
        .catch(error => {
            console.error(error)
        })
    }
    
    render() {
        return (
            <div>
                <h1>Análise de Aluno</h1>

                {
                <> 
                    <ul>
                        <li>ID: {this.state.aluno.id}</li>
                        <li>Nome: {this.state.aluno.name}</li>
                        <li>Classe: {this.state.aluno.classe}</li>
                        <li>ID do programa: {this.state.aluno.programaId}</li>
                        <li>Nome do programa: {this.state.aluno.programaName}</li>
                    <Link to={`/aluno/${this.state.aluno.id}/update`}><button type="button">Alterar aluno</button></Link>
                    <Link to= {'/aluno'}><button onClick={ () => this.deleteAluno(this.state.aluno.id)}>Remover aluno</button></Link>
                    </ul>
                </>
                }
                <Link to="/aluno"><button type="button">Voltar para alunos</button></Link>
            </div>
        )
    }
}

export default withRouter(AlunoById)

// useParams para function, withRouter para classs component