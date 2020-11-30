import React from 'react'
import MaterialTable from 'material-table'
import AutoRenewIcon from '@material-ui/icons/Autorenew'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'

const TableMentorInativo = ({ data }) => {
    let history = useHistory()

    const reactivateMentor = (id) => {
        console.log(id)
        httpService.post(`/mentor/reativacao/${id}`)
            .then(response => {
                history.push("/mentor")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (

        <MaterialTable title="Lista de mentores"
            localization={{
                header: {
                    actions: 'Reativar mentor',
                }
            }}
            data={data}
            columns={[
                {
                    title: 'Nome', field: 'name',
                    headerStyle: {
                        //backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'Idade', field: 'idade',
                    headerStyle: {
                        //backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'País', field: 'pais',
                },

                {
                    title: 'Escola', field: 'escola',
                },

                {
                    title: 'Programa', field: 'programaName',
                    headerStyle: {
                        //backgroundColor: '#039be5',
                    }
                },

            ]}
            options={{
                //search: false,
                //paging: false,
                //filtering: true,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: () => <AutoRenewIcon color="primary" />,
                    tooltip: 'Reativar mentor',
                    onClick: (event, rowData) => reactivateMentor(rowData.id)
                }
            ]}
        />
    )
}
export default TableMentorInativo