import {Button} from '@material-ui/core'
import { Link } from 'react-router-dom'

const ButtonAvaliacaoHome = () => {
    return(
<Link to="/avaliacao"><Button Button variant="contained" color="primary" style={{ margin: 8, minWidth: 225 }}>Voltar para avaliações</Button></Link>
    )
}
export default ButtonAvaliacaoHome