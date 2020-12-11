import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'


const TableAluno = () => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de alunos"
            localization={{
                header: {
                    actions: 'Ver aluno',
                }
            }}
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/aluno', {
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
                        //backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'Classe', field: 'classe',
                    headerStyle: {
                        //backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'Programa', field: 'programaName',
                    headerStyle: {
                        //backgroundColor: '#039be5',
                    }
                },

            ]}
            options={{
                search: false,
                //paging: false,
                //filtering: true,
                sorting: false,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: () => <Visibility color="primary" />,
                    //icon: 'visibility',
                    tooltip: 'Visualizar aluno',
                    onClick: (event, rowData) => history.push(`/aluno/${rowData.id}`)
                }
            ]}
        />
    )
}


export default TableAluno
