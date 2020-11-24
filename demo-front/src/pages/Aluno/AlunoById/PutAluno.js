/*import {useEffect, useState} from 'react'
import {useParams } from 'react-router-dom'
import httpService from '../../services/httpService'

const PutAluno = () => {
    const [aluno, setAluno] = React.useState({})
    
    const { id } = useParams()
    
    useEffect (() => {
        httpService(`/aluno/${id}`)
        .then(({data}) ={
            setAluno(data)
        })

    }, [])

    return <div>
        <h1> Alterar aluno {aluno.name}</h1>

    </div>
}

export default PutAluno*/