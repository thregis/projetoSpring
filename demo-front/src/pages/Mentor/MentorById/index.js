import React, { useEffect, useState } from 'react'
import httpService from '../../../services/httpService'
import { Link, useHistory, useParams } from 'react-router-dom'
import { Button, Card, CardActions, CardContent, Typography } from '@material-ui/core'
import ButtonEdit from '../../../components/Buttons/ButtonEdit'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonMentorHome from '../../../components/Buttons/ButtonMentorHome';

const MentorById = () => {
    let history = useHistory()
    const [mentor, setMentor] = useState({})

    const { id } = useParams()

    useEffect(() => {
        httpService.get(`/mentor/${id}`)
            .then(({ data }) => {
                setMentor(data)
            })
    }, [id])

    const deleteMentor = (id) => {
        httpService.delete(`/mentor/${id}`)
            .then(response => {
                alert('Success')
                history.push("/mentor")
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Análise de mentor</Typography>
            {
                <>
                <Card variant="outlined">
                    <CardContent>

                    <Typography color="primary">ID: {mentor.id}</Typography>
                    <Typography color="primary">Nome: {mentor.name}</Typography>
                    <Typography color="primary">Idade: {mentor.idade}</Typography>
                    <Typography color="primary">País: {mentor.pais}</Typography>
                    <Typography color="primary">Escola: {mentor.escola}</Typography>
                    <Typography color="primary">Programa: {mentor.programaName}</Typography>
                    </CardContent>
                    
                    <CardActions>
                    <Link to={`/mentor/${mentor.id}/update`}><ButtonEdit>Alterar mentor</ButtonEdit></Link>
                    <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteMentor(mentor.id)}><DeleteIcon/>Remover mentor</Button>   
                    </CardActions>

                </Card>
                </>
            }
            <ButtonMentorHome/>
        </div>
    )
}

export default MentorById