import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import { deserializeAvaliacao } from '../../../models/programa'

const TableAvaliacao = () => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de avaliações"
            localization={{
                header: {
                    actions: 'Ver avaliação',
                }
            }}
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/avaliacao', {
                            params: {
                                page: query.page,
                                size: query.pageSize
                            }
                        })
                            .then(({ data }) => {
                                resolve({
                                    data: data.content.map(deserializeAvaliacao),
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
                    title: 'Data', field: 'dataFormatada',
                    headerStyle: {

                    }
                },

            ]}
            options={{
                search: false,
                sorting: false,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: () => <Visibility color="primary" />,
                    tooltip: 'Visualizar avaliação',
                    onClick: (event, rowData) => history.push(`/avaliacao/${rowData.id}`)
                }
            ]}
        />
    )
}

export default TableAvaliacao