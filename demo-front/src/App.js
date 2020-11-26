import Index from './pages/Index'
import Aluno from './pages/Aluno/ListaAlunos'
import AlunoInativo from './pages/Aluno/Inativos'
import AlunoById from './pages/Aluno/AlunoById'
import AddAluno from './pages/Aluno/PostAluno'
import PutAluno from './pages/Aluno/AlunoById/PutAluno'
import Mentor from './pages/Mentor/ListaMentores'
import MentorInativo from './pages/Mentor/Inativos'
import MentorById from './pages/Mentor/MentorById'
import AddMentor from './pages/Mentor/PostMentor'
import PutMentor from './pages/Mentor/PutMentor'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
} from "react-router-dom"

function App() {
  return (
    <div>
      <Router>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/aluno">Alunos</Link>
      <Link to="/mentor">Mentores</Link>
      </nav>

        <Switch>
          <Route path="/" exact component={Index} />
          <Route path="/aluno" exact component={Aluno} />
          <Route path="/aluno/reativacao" exact component={AlunoInativo} />
          <Route path="/aluno/add" exact component={AddAluno} />
          <Route path="/aluno/:id" exact component={AlunoById} />
          <Route path="/aluno/:id/update" exact component={PutAluno} />

          <Route path="/mentor" exact component={Mentor} />
          <Route path="/mentor/reativacao" exact component={MentorInativo}/>
          <Route path="/mentor/add" exact component={AddMentor}/>
          <Route path="/mentor/:id" exact component={MentorById}/>
          <Route path="/mentor/:id/update" exact component={PutMentor}/>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
