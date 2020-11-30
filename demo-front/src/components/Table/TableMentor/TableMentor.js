import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'

const TableMentor = ({ data }) => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de mentores"
        localization={{
            header: {
              actions: 'Ver mentor',
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
                    icon: () => <Visibility color="primary" />,
                    //icon: 'visibility',
                    tooltip: 'Visualizar mentor',
                    onClick: (event, rowData) => history.push(`/mentor/${rowData.id}`)
                }
            ]}
        />
    )
}

export default TableMentor