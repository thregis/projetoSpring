import React from 'react'
import MaterialTable from 'material-table'
import AutorenewIcon from '@material-ui/icons/Autorenew';
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'

const TableAvaliacaoInativa = () => {
    let history = useHistory()

    const reactivateAvaliacao = (id) => {
        console.log(id)
        httpService.post(`/avaliacao/reativacao/${id}`)
        .then(response => {
            history.push("/avaliacao")
            console.log(response)
            })
    .catch(error => {
        console.error(error)
    })
}
    return (

        <MaterialTable title="Lista de avaliações"
        localization={{
            header: {
              actions: 'Reativar avaliacao',
            }
          }}          
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/avaliacao/reativacao', {
                            params: {
                                page: query.page,
                                size: query.pageSize
                            }
                        })
                            .then(({ data }) => {
                                resolve({
                                    data: data.content,
                                    page: data.pageable.pageNumber,
                                    totalCount: data.totalElements,
                                })
                            })
                    })
                )
            }
            columns={[
                {
                    title: 'Aluno', field: 'mentoriaAlunoName',
                    headerStyle: {
                    }
                },
                
                {
                    title: 'Mentor', field: 'mentoriaMentorName',
                    headerStyle: {
                    }
                },

                {
                    title: 'Disciplina', field: 'disciplinaName',
                    headerStyle: {

                    }
                },

                {
                    title: 'Nota', field: 'nota',
                    headerStyle: {
                    }
                },

                {
                    title: 'Data', field: 'data',
                    headerStyle: {

                    }
                },

            ]}
            options={{
                search: false,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: () => <AutorenewIcon color="primary" />,
                    tooltip: 'Reativar avaliação',
                    onClick: (event, rowData) => reactivateAvaliacao(rowData.id)
                }
            ]}
        />
    )
}
export default TableAvaliacaoInativa