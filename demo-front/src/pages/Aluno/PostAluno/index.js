import { Component } from 'react'
import httpService from '../../../services/httpService'
import { Link, useHistory } from 'react-router-dom'
import AlunoForm from '../../../components/Form/AlunoForm'
import { Button, Typography, withTheme } from '@material-ui/core'
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
                <Typography variant="h1" color="primary">Novo aluno</Typography>

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