import React, {useEffect} from 'react'
import { useParams, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import DisciplinaForm from '../../../components/Form/DisciplinaForm'
import { Typography } from '@material-ui/core'

const PutDisciplina = () => {
    let history = useHistory()
    const [disciplina, setDisciplina] = React.useState({})
    const [isFinished, setIsFinished] = React.useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/disciplina/${id}`)
            .then(({ data }) => {
                setDisciplina(data)
                setIsFinished(true)
            })

    }, [id])

    const handleSubmit = (disciplina) => {

        httpService.put(`/disciplina/${id}`, disciplina)
            .then(response => {
                history.push('/disciplina')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    return (<div>
        <Typography variant="h1" color="primary"> Alterar aluno: {disciplina.name}</Typography>

        {!isFinished && <p>Carregando...</p>}

        {isFinished &&
            <DisciplinaForm
                initialValues={{
                    name: disciplina.name,
                    descricao: disciplina.descricao,
                }}
                handleSubmit={handleSubmit}
            />
        }

    </div>
    )
}

export default PutDisciplina