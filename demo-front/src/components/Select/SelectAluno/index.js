import { MenuItem, TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../../services/httpService";

const SelectAluno = ({ value, onChange, id, label, name }) => {
  const [alunos, setAlunos] = useState([]);

  useEffect(() => {
    httpService.get("/aluno").then(({ data }) => {
      setAlunos(data);
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
      {alunos.map((aluno) => (
        <MenuItem key={aluno.id} value={aluno.id}>{aluno.name}</MenuItem>
      ))}
    </TextField>
  );
};

export default SelectAluno