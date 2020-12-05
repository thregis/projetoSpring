import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonProgramaHome = () => {
    return(
<Link to="/programa"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para programas</Button></Link>
    )
}

export default ButtonProgramaHome