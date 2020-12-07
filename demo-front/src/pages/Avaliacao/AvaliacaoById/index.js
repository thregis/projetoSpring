import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import {
    Link,
    useParams,
    useHistory,
  } from "react-router-dom"
import { Button, Typography, Card, CardActions, CardContent} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonEdit from '../../../components/Buttons/ButtonEdit';
import ButtonAvaliacaoHome from '../../../components/Buttons/ButtonAvaliacaoHome';
import {deserializeAvaliacao} from '../../../models/programa'


const AvaliacaoById = () => {
    let history = useHistory()

    const [avaliacao, setAvaliacao] = useState({})
    
    const {id} = useParams()

    useEffect(() => {
        httpService.get(`/avaliacao/${id}`)
        .then(({ data }) => {
            setAvaliacao(deserializeAvaliacao(data))
        })
    }, [id])


    const deleteAvaliacao = (id) => {
        httpService.delete(`/avaliacao/${id}`)
        .then(response => {
            alert('Success')
            history.push("/avaliacao")
        
        })
        .catch(error => {
            console.error(error)
        })
    }
    return (
        <div>
            <Typography variant="h1" color="primary">Análise de Avaliação</Typography>
    
            {
            <> 
            <Card variant="outlined">
                <CardContent>
                <Typography color="primary">ID: {avaliacao.id}</Typography>
                <Typography color="primary">Mentoria: {avaliacao.mentoriaId}</Typography>
                <Typography color="primary">Aluno: {avaliacao.mentoriaAlunoName}</Typography>
                <Typography color="primary">Mentor: {avaliacao.mentoriaMentorName}</Typography>
                <Typography color="primary">Disciplina: {avaliacao.disciplinaName}</Typography>
                <Typography color="primary">Data da avaliação: {avaliacao.data}</Typography>

                </CardContent>
                <CardActions>
                <Link to={`/avaliacao/${avaliacao.id}/update`}><ButtonEdit >Alterar avaliação</ButtonEdit></Link>
                <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteAvaliacao(avaliacao.id)}><DeleteIcon/>Remover avaliação</Button>    
                </CardActions>
            </Card>
               
            </>
            }
            <ButtonAvaliacaoHome/>
        </div>
    )
}
export default AvaliacaoById