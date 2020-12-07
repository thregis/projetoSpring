import { MenuItem, TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../../services/httpService";

const SelectMentoria = ({ value, onChange, id, label, name }) => {
  const [mentorias, setMentorias] = useState([]);

  useEffect(() => {
    httpService.get("/mentoria/lista").then(({ data }) => {
      setMentorias(data);
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
      {mentorias.map((mentoria) => (
        <MenuItem key={mentoria.id} value={mentoria.id}>{mentoria.alunoName} - {mentoria.mentorName}</MenuItem>
      ))}
    </TextField>
  );
};

export default SelectMentoria