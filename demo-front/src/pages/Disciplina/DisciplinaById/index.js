import React, {useEffect, useState} from 'react'
import httpService from '../../../services/httpService'
import {
    Link,
    useParams,
    useHistory,
  } from "react-router-dom"
import { Button, Typography, Card, CardActions, CardContent} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonEdit from '../../../components/Buttons/ButtonEdit';
import ButtonDisciplinaHome from '../../../components/Buttons/ButtonDisciplinaHome'


const DisciplinaById = () => {
    let history = useHistory()
    const [disciplina, setDisciplina] = useState({})

    const {id} = useParams()

    useEffect(() => {
        httpService.get(`/disciplina/${id}`)
        .then(({ data }) => {
            setDisciplina(data)
        })
    }, [id])


    const deleteDisciplina = (id) => {
        httpService.delete(`/disciplina/${id}`)
        .then(response => {
            alert('Success')
            history.push("/disciplina")
        
        })
        .catch(error => {
            console.error(error)
        })
    }
    return (
        <div>
            <Typography variant="h1" color="primary">Análise de disciplina</Typography>
    
            {
            <> 
            <Card variant="outlined">
                <CardContent>
                <Typography color="primary">ID: {disciplina.id}</Typography>
                <Typography color="primary">Nome: {disciplina.name}</Typography>
                <Typography color="primary">Descrição: {disciplina.descricao}</Typography>
                </CardContent>
                <CardActions>
                <Link to={`/disciplina/${disciplina.id}/update`}><ButtonEdit >Alterar disciplina</ButtonEdit></Link>
                <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deleteDisciplina(disciplina.id)}><DeleteIcon/>Remover disciplina</Button>    
                </CardActions>
            </Card>

            </>
            }
            <ButtonDisciplinaHome/>
        </div>
    )
}

export default DisciplinaById