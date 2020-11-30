import { MenuItem, TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../../services/httpService";

const SelectPrograma = ({ value, onChange, id, label, name }) => {
  const [programas, setProgramas] = useState([]);

  useEffect(() => {
    httpService.get("/programa").then(({ data }) => {
      setProgramas(data);
    });
  }, []);

  return (
<TextField
      id={id}
      select
      label={label}
      style={{ margin: 8 }}
      value={value}
      onChange={onChange}
      name={name}
      variant="outlined"
    >
      {programas.map((programa) => (
        <MenuItem key={programa.id} value={programa.id}>{programa.name}</MenuItem>
      ))}
    </TextField>
  );
};

export default SelectPrograma;
