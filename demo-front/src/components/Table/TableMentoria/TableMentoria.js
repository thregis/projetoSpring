import React from 'react'
import MaterialTable from 'material-table'
import { Visibility } from '@material-ui/icons'
import { useHistory } from 'react-router-dom'

const TableMentoria = ({ data }) => {
    let history = useHistory()
    return (

        <MaterialTable title="Lista de mentorias"
        localization={{
            header: {
              actions: 'Ver mentoria',
            }
          }}          
            data={data}
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