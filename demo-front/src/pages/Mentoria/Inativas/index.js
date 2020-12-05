import React from 'react'
import { Typography } from '@material-ui/core'
import ButtonMentoriaHome from '../../../components/Buttons/ButtonMentoriaHome'
import TableMentoriaInativa from '../../../components/Table/TableMentoria/TableMentoriaInativa'

const MentoriaInativa = () => {
    
    return (
        <div>

            <Typography variant="h1" color="primary">Mentorias inativas</Typography>
            <TableMentoriaInativa />
            <ButtonMentoriaHome />
        </div>
    )
}
export default MentoriaInativa