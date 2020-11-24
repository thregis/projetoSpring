import { Component } from 'react'
import httpService from '../../services/httpService'
import Input from '../../components/Input'
import { Link } from 'react-router-dom'
import AlunoForm from '../../components/AlunoForm'

class AddAluno extends Component {
    constructor() {
        super()

    }

    

    handleSubmit = (aluno) => {
    
        httpService.post('/aluno', aluno)
            .then(response => {
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    render() {
        
        return (
            <>
                <h1>Novo aluno</h1>

                <AlunoForm
                    initialValues={{
                        name: "",
                        classe: "",
                        programaId: 0,
                    }}
                    handleSubmit={this.handleSubmit}
                    />
                <Link to="/aluno"><button type="button">Voltar para alunos</button></Link>
            </>
        )
    }
}

export default AddAluno