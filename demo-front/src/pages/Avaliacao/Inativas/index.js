import React from 'react'
import { Typography } from '@material-ui/core'
import ButtonAvaliacaoHome from '../../../components/Buttons/ButtonAvaliacaoHome';
import TableAvaliacaoInativa from '../../../components/Table/TableAvaliacao/TableAvaliacaoInativa';

const AvaliacaoInativa = () => {

    return (

        <div>

            <Typography variant="h1" color="primary">Avaliações inativas</Typography>
            <TableAvaliacaoInativa />
            <ButtonAvaliacaoHome />

        </div>

    )
}
export default AvaliacaoInativa