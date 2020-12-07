import React, { useState, useEffect } from 'react'
import { useParams, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import ProgramaForm from '../../../components/Form/ProgramaForm'
import { Typography } from '@material-ui/core'
import {deserialize} from '../../../models/programa'


const PutPrograma = () => {
    let history = useHistory()
    const [programa, setPrograma] = useState({})
    const [isFinished, setIsFinished] = useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/programa/${id}`)
            .then(({ data }) => {
                setPrograma(deserialize(data))
                setIsFinished(true)
            })
    }, [id])

    const handleSubmit = (programa) => {

        httpService.put(`/programa/${id}`, programa)
            .then(response => {
                alert('Success')
                history.push("/programa")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Alterar programa: {programa.name}</Typography>

            {!isFinished && <p>Carregando</p>}

            {isFinished &&
                <ProgramaForm
                    initialValues={{
                        name: programa.name,
                        dataInicio: programa.dataInicio,
                        dataFinal: programa.dataFinal,
                    }}
                    handleSubmit={handleSubmit}
                />
                }
        </div>
    )
}

export default PutPrograma