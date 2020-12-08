import { Button, Typography } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import ButtonListAdd from '../../../components/Buttons/ButtonListAdd'
import TablePrograma from '../../../components/Table/TablePrograma/TablePrograma'

const Programa = () => {

    return (
        <div>
            <Typography variant="h1" color="primary">Programas ativos</Typography>

            <TablePrograma />

            <Link to="/programa/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Programas inativos</Button></Link>
            <Link to="/programa/add"><ButtonListAdd /></Link>
        </div>
    )
}

export default Programa