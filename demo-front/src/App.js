import Index from './pages/Index'
import Aluno from './pages/Aluno/ListaAlunos'
import AlunoInativo from './pages/Aluno/Inativos'
import AlunoById from './pages/Aluno/AlunoById'
import AddAluno from './pages/Aluno/PostAluno'
import PutAluno from './pages/Aluno/PutAluno'
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
import { Button, AppBar, IconButton, Toolbar } from '@material-ui/core/'
import SchoolIcon from '@material-ui/icons/School';
import Mentoria from './pages/Mentoria/ListaMentoria'
import MentoriaInativa from './pages/Mentoria/Inativas'
import AddMentoria from './pages/Mentoria/PostMentoria'
import MentoriaById from './pages/Mentoria/MentoriaById'
import PutMentoria from './pages/Mentoria/PutMentoria'
import DisciplinaById from './pages/Disciplina/DisciplinaById'
import Disciplina from './pages/Disciplina/ListaDisciplina'
import DisciplinaInativa from './pages/Disciplina/Inativas'
import AddDisciplina from './pages/Disciplina/PostDisciplina'
import PutDisciplina from './pages/Disciplina/PutDisciplina'
import Programa from './pages/Programa/ListaProgramas'
import ProgramaInativo from './pages/Programa/Inativos'
import AddPrograma from './pages/Programa/PostPrograma'
import ProgramaById from './pages/Programa/ProgramaById'
import PutPrograma from './pages/Programa/PutPrograma'
import Avaliacao from './pages/Avaliacao/ListaAvaliacao'
import AvaliacaoInativa from './pages/Avaliacao/Inativas'
import AvaliacaoById from './pages/Avaliacao/AvaliacaoById'
import AddAvaliacao from './pages/Avaliacao/PostAvaliacao'
import PutAvaliacao from './pages/Avaliacao/PutAvaliacao'

function App() {
  return (
    <div>
      <Router>

        <AppBar position="sticky">
          <Toolbar>
            <Link to="/"><IconButton edge="start" color="default" aria-label="menu"><SchoolIcon /></IconButton></Link>
            <Link to="/aluno"><Button variant="contained" color="primary">Alunos</Button></Link>
            <Link to="/mentor"><Button variant="contained" color="primary">Mentores</Button></Link>
            <Link to="/mentoria"><Button variant="contained" color="primary">Mentorias</Button></Link>
            <Link to="/disciplina"><Button variant="contained" color="primary">Disciplinas</Button></Link>
            <Link to="/programa"><Button variant="contained" color="primary">Programas</Button></Link>
            <Link to="/avaliacao"><Button variant="contained" color="primary">Avaliações</Button></Link>
          </Toolbar>
        </AppBar>

        <Switch>
          <Route path="/" exact component={Index} />

          <Route path="/aluno" exact component={Aluno} />
          <Route path="/aluno/reativacao" exact component={AlunoInativo} />
          <Route path="/aluno/add" exact component={AddAluno} />
          <Route path="/aluno/:id" exact component={AlunoById} />
          <Route path="/aluno/:id/update" exact component={PutAluno} />

          <Route path="/mentor" exact component={Mentor} />
          <Route path="/mentor/reativacao" exact component={MentorInativo} />
          <Route path="/mentor/add" exact component={AddMentor} />
          <Route path="/mentor/:id" exact component={MentorById} />
          <Route path="/mentor/:id/update" exact component={PutMentor} />

          <Route path="/mentoria" exact component={Mentoria} />
          <Route path="/mentoria/reativacao" exact component={MentoriaInativa} />
          <Route path="/mentoria/add" exact component={AddMentoria} />
          <Route path="/mentoria/:id" exact component={MentoriaById} />
          <Route path="/mentoria/:id/update" exact component={PutMentoria} />

          <Route path="/disciplina" exact component={Disciplina} />
          <Route path="/disciplina/reativacao" exact component={DisciplinaInativa} />
          <Route path="/disciplina/add" exact component={AddDisciplina} />
          <Route path="/disciplina/:id" exact component={DisciplinaById} />
          <Route path="/disciplina/:id/update" exact component={PutDisciplina} />

          <Route path="/programa" exact component={Programa} />
          <Route path="/programa/reativacao" exact component={ProgramaInativo} />
          <Route path="/programa/add" exact component={AddPrograma} />
          <Route path="/programa/:id" exact component={ProgramaById} />
          <Route path="/programa/:id/update" exact component={PutPrograma} />

          <Route path="/avaliacao" exact component={Avaliacao} />
          <Route path="/avaliacao/reativacao" exact component={AvaliacaoInativa} />
          <Route path="/avaliacao/add" exact component={AddAvaliacao} />
          <Route path="/avaliacao/:id" exact component={AvaliacaoById} />
          <Route path="/avaliacao/:id/update" exact component={PutAvaliacao} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
