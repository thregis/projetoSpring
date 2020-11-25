import React, { useEffect } from 'react'
import { useParams, Link } from 'react-router-dom'
import httpService from '../../../services/httpService'
import AlunoForm from '../../../components/AlunoForm'


const PutAluno = () => {
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
        <Link to="/aluno"><button type="button">Voltar para alunos</button></Link>

    </div>
    )
}

export default PutAluno