import React from 'react'
import MaterialTable from 'material-table'
import AutoRenewIcon from '@material-ui/icons/Autorenew'
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'

const TableMentoriaInativa = ({ data }) => {
    let history = useHistory()

    const reactivateMentoria = (id) => {
        console.log(id)
        httpService.post(`/mentoria/reativacao/${id}`)
            .then(response => {
                history.push("/mentoria")
                console.log(response)
            })
            .catch(error => {
                console.error(error)
            })
    }

    return (

        <MaterialTable title="Lista de mentorias"
            localization={{
                header: {
                    actions: 'Reativar mentoria',
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
                    icon: () => <AutoRenewIcon color="primary" />,
                    tooltip: 'Reativar mentoria',
                    onClick: (event, rowData) => reactivateMentoria(rowData.id)
                }
            ]}
        />
    )
}
export default TableMentoriaInativa