import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'


const TableDisciplina = () => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de disciplinas"
            localization={{
                header: {
                    actions: 'Ver disciplina',
                }
            }}
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/disciplina', {
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
                    title: 'Nome', field: 'name',
                    headerStyle: {
                    }
                },

                {
                    title: 'Descrição', field: 'descricao',
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
                    tooltip: 'Visualizar disciplina',
                    onClick: (event, rowData) => history.push(`/disciplina/${rowData.id}`)
                }
            ]}
        />
    )
}

export default TableDisciplina