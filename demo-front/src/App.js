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
import { Button, AppBar, IconButton, Toolbar} from '@material-ui/core/'
import SchoolIcon from '@material-ui/icons/School';
import Mentoria from './pages/Mentoria/ListaMentoria'
import MentoriaInativa from './pages/Mentoria/Inativas'
import AddMentoria from './pages/Mentoria/PostMentoria'
import MentoriaById from './pages/Mentoria/MentoriaById'
import PutMentoria from './pages/Mentoria/PutMentoria'

function App() {
  return (
    <div>
      <Router>

      <AppBar position="static">
            <Toolbar>
            <Link to="/"><IconButton edge="start" color="default" aria-label="menu"><SchoolIcon /></IconButton></Link>
                <Link to="/aluno"><Button variant="contained" color="primary">Alunos</Button></Link>
                <Link to="/mentor"><Button variant="contained" color="primary">Mentores</Button></Link>
                <Link to="/mentoria"><Button variant="contained" color="primary">Mentorias</Button></Link>
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
          <Route path="/mentor/reativacao" exact component={MentorInativo}/>
          <Route path="/mentor/add" exact component={AddMentor}/>
          <Route path="/mentor/:id" exact component={MentorById}/>
          <Route path="/mentor/:id/update" exact component={PutMentor}/>

          <Route path="/mentoria" exact component={Mentoria} />
          <Route path="/mentoria/reativacao" exact component={MentoriaInativa} />
          <Route path="/mentoria/add" exact component={AddMentoria} />
          <Route path="/mentoria/:id" exact component={MentoriaById} />
          <Route path="/mentoria/:id/update" exact component={PutMentoria} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
