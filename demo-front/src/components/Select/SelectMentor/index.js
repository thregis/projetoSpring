import { MenuItem, TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import httpService from "../../../services/httpService";

const SelectMentor = ({ value, onChange, id, label, name }) => {
  const [mentores, setMentores] = useState([]);

  useEffect(() => {
    httpService.get("/mentor").then(({ data }) => {
      setMentores(data);
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
      {mentores.map((mentor) => (
        <MenuItem key={mentor.id} value={mentor.id}>{mentor.name}</MenuItem>
      ))}
    </TextField>
  );
};
export default SelectMentor