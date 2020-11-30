import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonAlunoHome = () => {
    return(
<Link to="/aluno"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para alunos</Button></Link>
    )
}

export default ButtonAlunoHome


