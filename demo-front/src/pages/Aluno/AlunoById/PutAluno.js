import React, { useEffect } from 'react'
import { useParams, Link, useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import AlunoForm from '../../../components/AlunoForm'
import { Button } from '@material-ui/core'
import ButtonAlunoHome from '../../../components/Buttons/ButtonAlunoHome'


const PutAluno = () => {
    let history = useHistory()
    const [aluno, setAluno] = React.useState({})
    const [isFinished, setIsFinished] = React.useState(false)

    const { id } = useParams()

    useEffect(() => {
        httpService(`/aluno/${id}`)
            .then(({ data }) => {
                setAluno(data)
                setIsFinished(true)
            })

    }, [id])

    const handleSubmit = (aluno) => {

        httpService.put(`/aluno/${id}`, aluno)
            .then(response => {
                history.push('/aluno')
                alert('Success')
                console.log(response)
            }).catch(error => {
                console.error(error)
            })
    }

    return (<div>
        <h1> Alterar aluno: {aluno.name}</h1>

        {!isFinished && <p>Carregando...</p>}

        {isFinished &&
            <AlunoForm
                initialValues={{
                    name: aluno.name,
                    classe: aluno.classe,
                    programaId: aluno.programaId,
                }}
                handleSubmit={handleSubmit}
            />
        }

    </div>
    )
}

export default PutAluno