import Aluno from './pages/Aluno/Index'
import AlunoInativo from './pages/Aluno/Inativos'
import AlunoById from './pages/Aluno/AlunoById'
import PutAluno from './pages/Aluno/AlunoById/PutAluno'
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom"
import Index from './pages/Index'
import AddAluno from './pages/PostAluno'

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/" exact component={Index} />
          <Route path="/aluno" exact component={Aluno} />
          <Route path="/aluno/reativacao" exact component={AlunoInativo} />
          <Route path="/aluno/add" exact component={AddAluno} />
          <Route path="/aluno/:id" exact component={AlunoById} />
          <Route path="/aluno/:id/update" exact component={PutAluno} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
