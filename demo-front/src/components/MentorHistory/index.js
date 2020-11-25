import {useHistory} from 'react-router-dom'

    function HomeButton() {
        let history = useHistory()
        
        function redirect() {
            history.push("/mentor")
        }

        return (
            <button type="button" onClick={redirect}></button>
        )
    }

    export default HomeButton