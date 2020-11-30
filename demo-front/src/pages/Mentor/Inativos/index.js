import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import { Typography } from '@material-ui/core'
import ButtonMentorHome from '../../../components/Buttons/ButtonMentorHome'
import TableMentorInativo from '../../../components/Table/TableMentor/TableMentorInativo'

const MentorInativo = () => {
    const [mentores, setMentores] = useState([])

    useEffect(() => {
        httpService.get("/mentor/reativacao")
            .then(({ data }) => {
                setMentores(data)
            })
            .catch(error => {
                console.error(error)
            })
    }, [])

    return (
        <div>

            <Typography variant="h1" color="primary">Mentores inativos</Typography>
            <TableMentorInativo
                data={mentores}
            />
            <ButtonMentorHome />
        </div>
    )
}

export default MentorInativo