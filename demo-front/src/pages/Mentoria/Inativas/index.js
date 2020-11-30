import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import { Typography } from '@material-ui/core'
import ButtonMentoriaHome from '../../../components/Buttons/ButtonMentoriaHome'
import TableMentoriaInativa from '../../../components/Table/TableMentoria/TableMentoriaInativa'

const MentoriaInativa = () => {
    const [mentorias, setMentorias] = useState([])

    useEffect(() => {
        httpService.get("/mentoria/reativacao")
            .then(({ data }) => {
                setMentorias(data)
            })
            .catch(error => {
                console.error(error)
            })
    }, [])

    return (
        <div>

            <Typography variant="h1" color="primary">Mentorias inativas</Typography>
            <TableMentoriaInativa
                data={mentorias}
            />
            <ButtonMentoriaHome />
        </div>
    )
}
export default MentoriaInativa