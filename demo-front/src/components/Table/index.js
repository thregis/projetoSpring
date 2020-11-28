import React from 'react'
import MaterialTable from 'material-table'

const Table = ({titulo, dados, colunas}) => {
    const data=[
        {name: 'Ramesh', age:12},
        {name: 'Vikas', age: 35},
    ]
    const columns=[
        {title: 'Name',field:'name'},
        {title: 'Age', field: 'age'}
    ]
    
    return(
        <div>


            <MaterialTable title="Material Table"
            columns={columns}
            data={data}
            options={{
                search:false,
                paging:false,
                filtering: true,
                exportButton: true,
                rowStyle: {
                    backgroundColor: '#EEE',
                  }
            }}
            />

        </div>
    )
}


