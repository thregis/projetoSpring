import {Component, useEffect} from 'react'
import httpService from '../services/httpService'

const { useEffect } = require("react")
 
const selectPrograma = () => {
 const [programas, setProgramas] = React.useState([])
 const [selectedPrograma, setSelectedPrograma] = React.useState(null)
 
 useEffect(() => {
 httpService.get('/programa')
 .then(({ data }) => {
 setProgramas(data)
 })
 }, [])
 
 handleSubmit = () => {
 const body = {
 ...aluno,
 programaId: selectedPrograma
 }
 
 
 }
 
 return (<>
 <form>
 <select value={selectedPrograma} onChange={(event) => setSelectedPrograma(event.target.value)}>
 {
 programas.map(programa => (
 <option value={programa.id}>
 {programa.name}
 </option>
 ))
 }
 </select>
 
 </form>
 </>
 )
}