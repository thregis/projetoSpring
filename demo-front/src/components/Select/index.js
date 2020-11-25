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
      <label htmlFor={id}>
        {label}
      </label>
      <select id={id} value={value} onChange={onChange} name={name}>
      <option default>Selecione o programa</option>
        {programas.map((programa) => (
          <option key={programa.id} value={programa.id}>{programa.name}</option>
        ))}
      </select>
    </div>
  );
};

export default SelectPrograma;