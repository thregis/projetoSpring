import React from 'react'
import { Link } from 'react-router-dom'

function Index() {
    return (
        <>
        <h1>Projeto escola</h1>
        <p>Apanhando do front como se não houvesse amanhã</p>
        <Link to="/aluno">Alunos</Link>
        </>
    )

}

export default Index