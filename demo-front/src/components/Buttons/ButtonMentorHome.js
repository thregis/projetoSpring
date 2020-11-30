import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonMentorHome = () => {
    return(
<Link to="/mentor"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para mentores</Button></Link>
    )
}

export default ButtonMentorHome