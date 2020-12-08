import React from 'react'
import MaterialTable from 'material-table'
import AutorenewIcon from '@material-ui/icons/Autorenew';
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import { deserialize } from '../../../models/programa';

const TableProgramaInativo = () => {
    let history = useHistory()

    const reactivatePrograma = (id) => {
        console.log(id)
        httpService.post(`/programa/reativacao/${id}`)
        .then(response => {
            history.push("/programa")
            console.log(response)
            })
    .catch(error => {
        console.error(error)
    })
}
    return (

        <MaterialTable title="Lista de programas"
        localization={{
            header: {
              actions: 'Reativar programa',
            }
          }}          
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/programa/reativacao', {
                            params: {
                                page: query.page,
                                size: query.pageSize
                            }
                        })
                            .then(({ data }) => {
                                resolve({
                                    data: data.content.map(programa => deserialize(programa)),
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
                    title: 'Data de início', field: 'dataInicioFormatada',
                    headerStyle: {
                    }
                },

                {
                    title: 'Data de término', field: 'dataFinalFormatada',
                    headerStyle: {
                    }
                },

                /*{
                    title: 'Mentores vinculados', field: 'mentores',
                }*/

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
                    tooltip: 'Reativar programa',
                    onClick: (event, rowData) => reactivatePrograma(rowData.id)
                }
            ]}
        />
    )
}
export default TableProgramaInativo