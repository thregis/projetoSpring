import React from 'react'
import { Typography } from '@material-ui/core'
import ButtonProgramaHome from '../../../components/Buttons/ButtonProgramaHome';
import TableProgramaInativo from '../../../components/Table/TablePrograma/TableProgramaInativo';

const ProgramaInativo = () => {

    return (

        <div>

            <Typography variant="h1" color="primary">Programas inativos</Typography>
            <TableProgramaInativo />
            <ButtonProgramaHome />

        </div>

    )
}

export default ProgramaInativo