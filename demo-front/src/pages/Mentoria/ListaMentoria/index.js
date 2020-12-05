import React from 'react'
import { Link } from 'react-router-dom'
import { Button, Typography } from '@material-ui/core'
import TableMentoria from '../../../components/Table/TableMentoria/TableMentoria'
import ButtonGroupAdd from '../../../components/Buttons/ButtonGroupAdd'

const Mentoria = () => {
    
    return (
        <div>
            <Typography variant="h1" color="primary">Mentorias ativas</Typography>
            <TableMentoria />
            <Link to="/mentoria/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Mentorias inativos</Button></Link>
            <Link to="/mentoria/add"><ButtonGroupAdd /></Link>
        </div>
    )

}
export default Mentoria