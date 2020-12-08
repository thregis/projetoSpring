import { Button, Typography } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import ButtonPostAdd from '../../../components/Buttons/ButtonPostAdd'
import TableAvaliacao from '../../../components/Table/TableAvaliacao/TableAvaliacao'

const Avaliacao = () => {

    return (
        <div>
            <Typography variant="h1" color="primary">Avaliações ativas</Typography>

            <TableAvaliacao />

            <Link to="/avaliacao/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Avaliações inativas</Button></Link>
            <Link to="/avaliacao/add"><ButtonPostAdd /></Link>
        </div>
    )
}

export default Avaliacao