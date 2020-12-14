import '@testing-library/jest-dom/extend-expect';
import { render, waitFor, screen } from '@testing-library/react'
import AlunoInativo from "./"
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
  } from "react-router-dom"
  import httpService from '../../../services/httpService'

const mockAlunosInativos = {
    "content": [
        {
            "id": 3,
            "name": "Juca",
            "classe": "classe",
            "programaId": 1,
            "programaName": "programa",
            "active": true
        },
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 3,
    "totalElements": 12,
    "last": false,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 5,
    "first": true,
    "numberOfElements": 5,
    "empty": false
}

describe("listagem de alunos inativos", () => {
    test('deve exibir título de alunos inativos corretamente', () => {
        const {getByText}=render(<Router><AlunoInativo/></Router>)
        expect(getByText('Alunos inativos')).toBeInTheDocument()
    })

    test('deve mostrar tabela com alunos inativos', async () => {
        const resp = {data: mockAlunosInativos}
        const httpSpy = jest.spyOn(httpService, 'get').mockResolvedValue(resp);        
        const {getByText} =render(<Router><AlunoInativo/></Router>)
        await waitFor(() => expect(httpSpy).toHaveBeenCalledTimes(1))
        expect(getByText("Juca")).toBeInTheDocument()
    })

})