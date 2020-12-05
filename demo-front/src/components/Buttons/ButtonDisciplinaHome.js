import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonDisciplinaHome = () => {
    return(
<Link to="/disciplina"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para disciplinas</Button></Link>
    )
}

export default ButtonDisciplinaHome


