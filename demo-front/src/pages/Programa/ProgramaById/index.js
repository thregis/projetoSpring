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
import ButtonProgramaHome from '../../../components/Buttons/ButtonProgramaHome';
import {deserialize} from '../../../models/programa'


const ProgramaById = () => {
    let history = useHistory()

    const [programa, setPrograma] = useState({})
    
    const {id} = useParams()

    useEffect(() => {
        httpService.get(`/programa/${id}`)
        .then(({ data }) => {
            setPrograma(deserialize(data))
        })
    }, [id])


    const deletePrograma = (id) => {
        httpService.delete(`/programa/${id}`)
        .then(response => {
            alert('Success')
            history.push("/programa")
        
        })
        .catch(error => {
            console.error(error)
        })
    }
    return (
        <div>
            <Typography variant="h1" color="primary">Análise de Programa</Typography>
    
            {
            <> 
            <Card variant="outlined">
                <CardContent>
                <Typography color="primary">ID: {programa.id}</Typography>
                <Typography color="primary">Nome: {programa.name}</Typography>
                <Typography color="primary">Data de início: {programa.dataInicio}</Typography>
                <Typography color="primary">Data de término: {programa.dataFinal}</Typography>

                    {programa.mentores &&
                    <ul>
                <Typography variant="h5" color="primary"> Mentores vinculados: </Typography> {programa.mentores.map( (mentor) =>(
                    <Typography color="primary"><li>{mentor.name}</li></Typography>
                    ))}
                    </ul>}

                </CardContent>
                <CardActions>
                <Link to={`/programa/${programa.id}/update`}><ButtonEdit >Alterar programa</ButtonEdit></Link>
                <Button style={{ margin: 8 }} variant="contained" color="secondary" onClick={ () => deletePrograma(programa.id)}><DeleteIcon/>Remover programa</Button>    
                </CardActions>
            </Card>
               
            </>
            }
            <ButtonProgramaHome/>
        </div>
    )
}

export default ProgramaById