export const deserialize = programa => ({
    ...programa,
    dataInicio: new Date(programa.dataInicio).toISOString().split('T')[0],
    dataFinal: new Date(programa.dataFinal).toISOString().split('T')[0],
    dataInicioFormatada: new Date(programa.dataInicio).toLocaleDateString(),
    dataFinalFormatada: new Date(programa.dataFinal).toLocaleDateString(),
   })


export const deserializeAvaliacao = avaliacao => ({
    ...avaliacao,
    data: new Date(avaliacao.data).toISOString().split('T')[0],
    dataFormatada: new Date(avaliacao.data).toLocaleDateString(),
})

export const color = (event) =>{
    if (event.length< 3){
        return "secondary"
    }
        return 'primary'
}

/*
export function validateInfo(value){
    let errors = {}

    if(!value.name.trim()){
        errors.name = "é necessário preencher o campo Nome"
    }else if(!value.name.length>3){
        errors.name = "Nome precisa ter no mínimo 3 caracteres"
    }else if(!value.name.length<50){
        errors.name = "Nome precisa ter no máximo 50 caracteres"
    }

}*/