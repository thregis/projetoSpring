import React from 'react'
import MaterialTable from 'material-table'
import { Link } from 'react-router-dom'
import { Visibility } from '@material-ui/icons'

const TableAluno = ({ data }) => {

    return (

        <MaterialTable title="Alunos ativos"

            data={data}
            columns={[
                {
                    title: 'Nome', field: 'name',
                    headerStyle: {
                        backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'Classe', field: 'classe',
                    headerStyle: {
                        backgroundColor: '#039be5',
                    }
                },

                {
                    title: 'Programa', field: 'programaName',
                    headerStyle: {
                        backgroundColor: '#039be5',
                    }
                },

            ]}
            options={{
                //search: false,
                paging: false,
                //filtering: true,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                }
            }}
            actions={[
                {
                    icon: (rowData) => <Link to={`/aluno/${rowData.id}`}><Visibility color="primary" /></Link>,
                    //icon: 'visibility',
                    tooltip: 'Visualizar aluno',
                    //onClick: (rowData) => {<Link to={`/aluno/${rowData.id}`}>Inspecionar</Link>}
                }
            ]}
        />
    )
}


export default TableAluno
