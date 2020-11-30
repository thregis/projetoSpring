import React, { useState, useEffect } from 'react'
import httpService from '../../../services/httpService'
import { Link } from 'react-router-dom'
import { Button, Typography } from '@material-ui/core'
import TableMentor from '../../../components/Table/TableMentor/TableMentor'
import ButtonPersonAdd from '../../../components/Buttons/ButtonPersonAdd'

const Mentor = () => {
    const [mentores, setMentores] = useState([])

    useEffect(() => {
        httpService.get('/mentor')
            .then(({ data }) => {
                setMentores(data)
            })
            .catch(error => {
                console.error(error)
            })

    }, [])

    return (
        <div>
            <Typography variant="h1" color="primary">Mentores ativos</Typography>
            <TableMentor
                data={mentores}
            />
            <Link to="/mentor/reativacao"><Button variant="contained" color="primary" style={{ margin: 8 }} >Mentores inativos</Button></Link>
            <Link to="/mentor/add"><ButtonPersonAdd /></Link>
        </div>
    )

}

export default Mentor