import { Component } from 'react'
import httpService from '../../../services/httpService'
import { Link, useHistory } from 'react-router-dom'
import AlunoForm from '../../../components/AlunoForm'
import { Button, withTheme } from '@material-ui/core'
import ButtonAlunoHome from '../../../components/Buttons/ButtonAlunoHome'

const AddAluno = () =>{
    let history = useHistory()
    const handleSubmit = (aluno) => {
    
        httpService.post('/aluno', aluno)
            .then(response => {
                history.push('/aluno')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }
    
        return (
            <>
                <h1>Novo aluno</h1>

                <AlunoForm
                    initialValues={{
                        name: "",
                        classe: "",
                        programaId: 0,
                    }}
                    handleSubmit={handleSubmit}
                    />
            </>
        )
    }

export default AddAluno