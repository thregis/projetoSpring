import '@testing-library/jest-dom/extend-expect';
import { render, waitFor, screen } from '@testing-library/react'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useParams,
    MemoryRouter
  } from "react-router-dom"
  import httpService from '../../../services/httpService'
import AlunoById from './';


describe("Descrição de aluno", () => {
    test('deve exibir título da página corretamente', () => {
        const {getByText}=render(<Router><AlunoById/></Router>)
        expect(getByText('Análise de aluno')).toBeInTheDocument()
    })

    /*
    test('deve mostrar o card com o aluno', async () => {
        const aluno = {name: 'Juca', classe: 'classe', programaName: 'programa', id: 1, active: true}
        const {getByText}=render(<MemoryRouter initialEntries={["/aluno/1"]}><AlunoById /></MemoryRouter>)
        const resp = {aluno}
        const httpSpy = jest.spyOn(httpService, 'get').mockResolvedValue(resp);
        await waitFor(() => expect(httpSpy).toHaveBeenCalledTimes(1))
        expect(getByText('Juca')).toBeInTheDocument()
    })*/
})