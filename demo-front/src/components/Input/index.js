import { TextField } from "@material-ui/core"

const Input = ({ label, id, ...props }) => (
    <div>
        <label htmlFor={id}>
            {label}
        </label>
        <TextField id={id} variant="outlined" {...props}/>
        
    </div>

)

export default Input