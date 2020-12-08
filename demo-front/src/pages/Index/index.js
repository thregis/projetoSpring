import { Typography } from '@material-ui/core'
import React from 'react'
import { Link } from 'react-router-dom'
import { makeStyles } from '@material-ui/core/styles'

const  useStyles = makeStyles({
    projetoEscolaStyle: {
        fontStyle: 'roboto',
        color: 'black'
    }
})

function Index() {
    const classes = useStyles();
    return (
        <>
        <Typography className={classes.projetoEscolaStyle} variant="h1" color="primary" style={{margin: 8}}>Projeto escola</Typography>
        <Typography color="secondary" style={{margin: 8}}>Apanhando do front como se não houvesse amanhã</Typography>
        <Typography color="primary" style={{margin: 8}}>Integração do projeto com o front e aplicação de estilos.</Typography>
        <Typography color="primary" style={{margin: 8}}>E não sei mais o que colocar nessa página.</Typography>
        </>
    )

}

export default Index