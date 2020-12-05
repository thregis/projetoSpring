import React from 'react'
import { Link } from 'react-router-dom'
import { Button, Typography } from '@material-ui/core'
import TableMentor from '../../../components/Table/TableMentor/TableMentor'
import ButtonPersonAdd from '../../../components/Buttons/ButtonPersonAdd'

const Mentor = () => {

    return (
        <div>
            <Typography variant="h1" color="primary">Mentores ativos</Typography>
            <TableMentor />
            <Link to="/mentor/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Mentores inativos</Button></Link>
            <Link to="/mentor/add"><ButtonPersonAdd /></Link>
        </div>
    )

}

export default Mentor