import '@testing-library/jest-dom/extend-expect';
import { render, waitFor, screen } from '@testing-library/react'
import Aluno from "./"
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
  } from "react-router-dom"
  import httpService from '../../../services/httpService'

//jest.mock('httpService')
//jest.spyOn(httpService, 'get').mockResolvedValue(resp);
const mockAlunos = {
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

describe("listagem de aluno", () => {
    test('deve exibir título de alunos corretamente', () => {
        const {getByText}=render(<Router><Aluno/></Router>)
        expect(getByText('Alunos ativos')).toBeInTheDocument()
    })

    test('deve mostrar tabela com alunos', async () => {
        //const alunos = [{name: 'Juca', classe: 'classe', programaName: 'programa'}]
        const resp = {data: mockAlunos}
        //httpService.get.mockResolvedValue(resp)
        const httpSpy = jest.spyOn(httpService, 'get').mockResolvedValue(resp);
        
        const {getByText, debug} =render(<Router><Aluno/></Router>)
        //screen.debug()
        await waitFor(() => expect(httpSpy).toHaveBeenCalledTimes(1))
        expect(getByText("Juca")).toBeInTheDocument()
        //expect(httpSpy).toHaveBeenCalled()
    })

})