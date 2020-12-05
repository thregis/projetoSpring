import React from 'react'
import { Typography} from '@material-ui/core'
import ButtonDisciplinaHome from '../../../components/Buttons/ButtonDisciplinaHome';
import TableDisciplinaInativa from '../../../components/Table/TableDisciplina/TableDisciplinaInativa';

const DisciplinaInativa = () => {

return(

    <div>

    <Typography variant="h1" color="primary">Disciplinas inativas</Typography>
   <TableDisciplinaInativa />
   <ButtonDisciplinaHome/>
   </div>
)
}

export default DisciplinaInativa