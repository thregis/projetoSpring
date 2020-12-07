import { MenuItem, TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../../services/httpService";

const SelectDisciplina = ({ value, onChange, id, label, name }) => {
  const [disciplinas, setDisciplinas] = useState([]);

  useEffect(() => {
    httpService.get("/disciplina/lista").then(({ data }) => {
      setDisciplinas(data);
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
      {disciplinas.map((disciplina) => (
        <MenuItem key={disciplina.id} value={disciplina.id}>{disciplina.name}</MenuItem>
      ))}
    </TextField>
  );
};

export default SelectDisciplina