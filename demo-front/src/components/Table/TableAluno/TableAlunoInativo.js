import React from 'react'
import MaterialTable from 'material-table'
import AutorenewIcon from '@material-ui/icons/Autorenew';
import { useHistory } from 'react-router-dom'
import httpService from '../../../services/httpService'

const TableAlunoInativo = ({ data }) => {
    let history = useHistory()

    const reactivateAluno = (id) => {
        console.log(id)
        httpService.post(`/aluno/reativacao/${id}`)
        .then(response => {
            history.push("/aluno")
            console.log(response)
            })
    .catch(error => {
        console.error(error)
    })
}
    return (

        <MaterialTable title="Lista de alunos"
        localization={{
            header: {
              actions: 'Reativar aluno',
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
                    icon: () => <AutorenewIcon color="primary" />,
                    //icon: 'autorenew',
                    tooltip: 'Reativar aluno',
                    onClick: (event, rowData) => reactivateAluno(rowData.id)
                }
            ]}
        />
    )
}


export default TableAlunoInativo
