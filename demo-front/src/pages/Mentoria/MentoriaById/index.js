import React, { useEffect, useState } from 'react'
import httpService from '../../../services/httpService'
import { Link, useHistory, useParams } from 'react-router-dom'
import { Button, Card, CardActions, CardContent, Typography } from '@material-ui/core'
import ButtonEdit from '../../../components/Buttons/ButtonEdit'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonMentoriaHome from '../../../components/Buttons/ButtonMentoriaHome';

const MentoriaById = () => {
    let history = useHistory()
    const [mentoria, setMentoria] = useState({})

    const { id } = useParams()

    useEffect(() => {
        httpService.get(`/mentoria/${id}`)
            .then(({ data }) => {
                setMentoria(data)
            })
    }, [id])

    const deleteMentoria = (id) => {
        httpService.delete(`/mentoria/${id}`)
            .then(response => {
                alert('Success')
                history.push("/mentoria")
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Análise de mentoria</Typography>
            {
                <>
                <Card variant="outlined">
                    <CardContent>

                    <Typography color="primary">ID da mentoria: {mentoria.id}</Typography>
                    <Typography color="primary" variant="h6">Aluno</Typography>
                    <Typography color="primary">ID do aluno: {mentoria.alunoId}</Typography>
                    <Typography color="primary">Nome do aluno: {mentoria.alunoName}</Typography>
                    <Typography color="primary" variant="h6">Mentor</Typography>
                    <Typography color="primary">ID do mentor: {mentoria.mentorId}</Typography>
                    <Typography color="primary">Nome do aluno: {mentoria.mentorName}</Typography>
                    </CardContent>
                    
                    <CardActions>
                    <Link to={`/mentoria/${mentoria.id}/update`}><ButtonEdit>Alterar mentoria</ButtonEdit></Link>
                    <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteMentoria(mentoria.id)}><DeleteIcon/>Remover mentoria</Button>   
                    </CardActions>

                </Card>
                </>
            }
            <ButtonMentoriaHome/>
        </div>
    )
}
export default MentoriaById