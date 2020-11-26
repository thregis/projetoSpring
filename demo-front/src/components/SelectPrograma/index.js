import { FormControl, InputLabel, MenuItem, Select } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../services/httpService";

const SelectPrograma = ({ value, onChange, id, label, name }) => {
  const [programas, setProgramas] = useState([]);

  useEffect(() => {
    httpService.get("/programa").then(({ data }) => {
      setProgramas(data);
    });
  }, []);

  return (
    <div>
      <FormControl variant="outlined">
      <label htmlFor={id}>
        {label}
      </label>
      {/*<InputLabel id="programa">Selecione o programa</InputLabel>*/}
      {/*labelId="programa"*/}
      <Select 
      id={id} 
      value={value} 
      onChange={onChange} 
      autoWidth
      name={name}>
      
        {programas.map((programa) => (
          <MenuItem key={programa.id} value={programa.id}>{programa.name}</MenuItem>
        ))}
      </Select>
      </FormControl>
    </div>
  );
};

export default SelectPrograma;