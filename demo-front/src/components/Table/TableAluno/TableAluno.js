import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'

const TableAluno = ({ data }) => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de alunos"
        localization={{
            header: {
              actions: 'Ver aluno',
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
                    tooltip: 'Visualizar aluno',
                    onClick: (event, rowData) => history.push(`/aluno/${rowData.id}`)
                }
            ]}
        />
    )
}


export default TableAluno
