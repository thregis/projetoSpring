import React, { useState } from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'
import {deserialize} from '../../../models/programa'


const TablePrograma = () => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de programas"
            localization={{
                header: {
                    actions: 'Ver programa',
                }
            }}
            data={
                query => (
                    new Promise((resolve, reject) => {
                        httpService.get('/programa', {
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
                    title: 'Data de início', field: 'dataInicio',
                    headerStyle: {

                    }
                },

                {
                    title: 'Data de Término', field: 'dataFinal',
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
                    icon: () => <Visibility color="primary" />,
                    tooltip: 'Visualizar programa',
                    onClick: (event, rowData) => history.push(`/programa/${rowData.id}`)
                }
            ]}
        />
    )
}
export default TablePrograma