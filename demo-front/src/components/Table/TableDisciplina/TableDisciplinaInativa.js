import React from 'react'
import MaterialTable from 'material-table'
import AutorenewIcon from '@material-ui/icons/Autorenew';
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'

const TableDisciplinaInativa = () => {
    let history = useHistory()

    const reactivateDisciplina = (id) => {
        console.log(id)
        httpService.post(`/disciplina/reativacao/${id}`)
        .then(response => {
            history.push("/disciplina")
            console.log(response)
            })
    .catch(error => {
        console.error(error)
    })
}
    return (

        <MaterialTable title="Lista de disciplinas"
        localization={{
            header: {
              actions: 'Reativar disciplina',
            }
          }}          
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/disciplina/reativacao', {
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
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: () => <AutorenewIcon color="primary" />,
                    tooltip: 'Reativar aluno',
                    onClick: (event, rowData) => reactivateDisciplina(rowData.id)
                }
            ]}
        />
    )
}

export default TableDisciplinaInativa