import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'


const TableMentoria = () => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de mentorias"
        localization={{
            header: {
              actions: 'Ver mentoria',
            }
          }}          
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/mentoria', {
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
                    title: 'Aluno', field: 'alunoName',
                    headerStyle: {
                    }
                },
                {
                    title: 'Mentor', field: 'mentorName',
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
                    //icon: 'visibility',
                    tooltip: 'Visualizar mentoria',
                    onClick: (event, rowData) => history.push(`/mentoria/${rowData.id}`)
                }
            ]}
        />
    )
}

export default TableMentoria