export const deserialize = programa => ({
    ...programa,
    dataInicio: new Date(programa.dataInicio).toLocaleDateString(),
    dataFinal: new Date(programa.dataFinal).toLocaleDateString(),
   })


export const deserializeAvaliacao = avaliacao => ({
    ...avaliacao,
    data: new Date(avaliacao.data).toLocaleDateString(),
})

export const color = (event) =>{
    if (event.length >0 && event.length< 3){
        return "secondary"
    }
        return 'primary'
}