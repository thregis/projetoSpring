import React from 'react'
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
                                    data: data.content.map(deserialize),
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
                    //type: 'date',
                },

                {
                    title: 'Data de Término', field: 'dataFinalFormatada',
                    //type: 'date',
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