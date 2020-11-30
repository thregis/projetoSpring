import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonMentoriaHome = () => {
    return(
<Link to="/mentoria"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para mentorias</Button></Link>
    )
}
export default ButtonMentoriaHome