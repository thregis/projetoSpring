import { Button, Typography } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import TableDisciplina from '../../../components/Table/TableDisciplina/TableDisciplina'
import ButtonListAdd from '../../../components/Buttons/ButtonListAdd'

const Disciplina = () => {
    
    return (
        <div>
            <Typography variant="h1" color="primary">Disciplinas ativas</Typography>

            <TableDisciplina />

            <Link to="/disciplina/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Disciplinas inativas</Button></Link>
            <Link to="/disciplina/add"><ButtonListAdd/></Link>
        </div>
    )

}

export default Disciplina
