import React, { useState, useEffect } from 'react'
import { useParams, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import { Typography } from '@material-ui/core'
import {deserializeAvaliacao} from '../../../models/programa'
import AvaliacaoForm from '../../../components/Form/AvaliacaoForm'


const PutAvaliacao = () => {
    let history = useHistory()
    const [avaliacao, setAvaliacao] = useState({})
    const [isFinished, setIsFinished] = useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/avaliacao/${id}`)
            .then(({ data }) => {
                setAvaliacao(deserializeAvaliacao(data))
                setIsFinished(true)
            })
    }, [id])

    const handleSubmit = (avaliacao) => {

        httpService.put(`/avaliacao/${id}`, avaliacao)
            .then(response => {
                alert('Success')
                history.push("/avaliacao")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (
        <div>
            <Typography variant="h1" color="primary">Alterar avaliacao:</Typography>

            {!isFinished && <p>Carregando</p>}

            {isFinished &&
                <AvaliacaoForm
                    initialValues={{

                        mentoriaId: avaliacao.mentoriaId,
                        disciplinaId: avaliacao.disciplinaId,
                        nota: avaliacao.nota,
                        data: avaliacao.data,
                    }}
                    handleSubmit={handleSubmit}
                />
                }
        </div>
    )
}

export default PutAvaliacao