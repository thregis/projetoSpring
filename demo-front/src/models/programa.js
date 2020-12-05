export const deserialize = programa => ({
    ...programa,
    dataInicio: new Date(programa.dataInicio).toLocaleDateString(),
    dataFinal: new Date(programa.dataFinal).toLocaleDateString(),
   })