import { TextField } from "@material-ui/core"

const Input = ({ label, id, ...props }) => (
    <div>
    {/*}    <label htmlFor={id}>
            {label}
</label> */}
        <TextField label={label} id={id} style={{ margin: 8 }} variant="outlined" {...props}/>
        
    </div>

)

export default Input