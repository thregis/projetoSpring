import React, { useEffect, useState } from 'react'
import httpService from '../../../services/httpService'
import { IconButton} from '@material-ui/core'
import AutorenewIcon from '@material-ui/icons/Autorenew';
import ButtonAlunoHome from '../../../components/Buttons/ButtonAlunoHome';
import { useHistory } from 'react-router-dom'
import TableAlunoInativo from '../../../components/Table/TableAlunoInativo';

const AlunoInativo = () =>{

    const [alunos, setAlunos] = useState([])

    useEffect (() => {
        httpService.get('/aluno/reativacao')
        .then(({data}) => {
            setAlunos(data)
        })
        .catch(error => {
            console.error(error)
        })

    },[])
    /*
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
}*/
        
        return(
            /*
            <div>
                <h1>Alunos Inativos</h1>

                <table>
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Classe</th>
                        <th>Programa</th>
                        <th>Reativação</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        alunos.map(aluno =>
                            <tr key={aluno.id}>
                                <td>{aluno.name}</td>
                                <td>{aluno.classe}</td>
                                <td>{aluno.programaName}</td>
                                <td><IconButton color="primary" onClick={ () => reactivateAluno(aluno.id)}><AutorenewIcon/></IconButton></td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
                <ButtonAlunoHome/>

            </div>
            */
           <div>

           <TableAlunoInativo
           data={alunos}
           />
           <ButtonAlunoHome/>
           </div>
           
        )
    }


export default AlunoInativo