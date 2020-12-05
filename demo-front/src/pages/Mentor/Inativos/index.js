import React from 'react'
import { Typography } from '@material-ui/core'
import ButtonMentorHome from '../../../components/Buttons/ButtonMentorHome'
import TableMentorInativo from '../../../components/Table/TableMentor/TableMentorInativo'

const MentorInativo = () => {
    
    return (
        <div>

            <Typography variant="h1" color="primary">Mentores inativos</Typography>
            <TableMentorInativo />
            <ButtonMentorHome />
        </div>
    )
}

export default MentorInativo