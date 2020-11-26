import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import {
    Link,
    useParams,
    //useHistory
  } from "react-router-dom"
import { Button} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete';
import ButtonEdit from '../../../components/Buttons/ButtonEdit';
import ButtonAlunoHome from '../../../components/Buttons/ButtonAlunoHome';


const AlunoById = () => {
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
            /*let history = useHistory()
            history.push("/aluno")            se fosse hook */
        
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
                <ul>
                    <li>ID: {aluno.id}</li>
                    <li>Nome: {aluno.name}</li>
                    <li>Classe: {aluno.classe}</li>
                    <li>ID do programa: {aluno.programaId}</li>
                    <li>Nome do programa: {aluno.programaName}</li>
                <Link to={`/aluno/${aluno.id}/update`}><ButtonEdit>Alterar aluno</ButtonEdit></Link>
                <Link to= {'/aluno'}><Button variant="contained" color="secondary" onClick={ () => deleteAluno(aluno.id)}><DeleteIcon/>Remover aluno</Button></Link>
                </ul>
            </>
            }
            <ButtonAlunoHome/>
        </div>
    )
}

export default AlunoById

// useParams para function, withRouter para classs component