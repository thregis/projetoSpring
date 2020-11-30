import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import {
    Link,
    useParams,
    //useHistory
  } from "react-router-dom"
import { Button, Typography, Card, CardActions, CardContent} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonEdit from '../../../components/Buttons/ButtonEdit';
import ButtonAlunoHome from '../../../components/Buttons/ButtonAlunoHome';
import { useHistory } from 'react-router-dom'


const AlunoById = () => {
    let history = useHistory()
    const [aluno, setAluno] = useState({})
    
    const {id} = useParams()

    useEffect(() => {
        httpService.get(`/aluno/${id}`)
        .then(({ data }) => {
            setAluno(data)
        })
    }, [id])


    const deleteAluno = (id) => {
        httpService.delete(`/aluno/${id}`)
        .then(response => {
            alert('Success')
            history.push("/aluno")
        
        })
        .catch(error => {
            console.error(error)
        })
    }
    return (
        <div>
            <h1>Análise de aluno</h1>
    
            {
            <> 
            <Card>
                <CardContent>
                <Typography color="primary">ID: {aluno.id}</Typography>
                <Typography color="primary">Nome: {aluno.name}</Typography>
                <Typography color="primary">ID do programa: {aluno.programaId}</Typography>
                <Typography color="primary">Nome do programa: {aluno.programaName}</Typography>
                </CardContent>
                <CardActions>
                <Link to={`/aluno/${aluno.id}/update`}><ButtonEdit >Alterar aluno</ButtonEdit></Link>
                <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteAluno(aluno.id)}><DeleteIcon/>Remover aluno</Button>    
                </CardActions>
            </Card>
               {/*} <ul>
                    <li><Typography color="primary">ID: {aluno.id}</Typography></li>
                    <li>Nome: {aluno.name}</li>
                    <li>Classe: {aluno.classe}</li>
                    <li>ID do programa: {aluno.programaId}</li>
                    <li>Nome do programa: {aluno.programaName}</li>
                <Link to={`/aluno/${aluno.id}/update`}><ButtonEdit >Alterar aluno</ButtonEdit></Link>
                <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteAluno(aluno.id)}><DeleteIcon/>Remover aluno</Button>
            </ul> */}
            </>
            }
            <ButtonAlunoHome/>
        </div>
    )
}

export default AlunoById

// useParams para function, withRouter para classs component