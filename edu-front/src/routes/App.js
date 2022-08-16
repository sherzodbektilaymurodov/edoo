import {Provider} from "react-redux";
import store from "../redux";
import {Route, Routes} from "react-router-dom";
import NotFound from "../page/NotFound";
import Subject from "../page/Subject";
import Register from "../page/Register";
import Pupil from "../page/Pupil";
import Teacher from "../page/Teacher";

function App() {
  return (
      <Provider store={store}>
        <Routes>
          <Route path='/admin/subject' element={<Subject/>}/>
          <Route path='/admin/register' element={<Register/>}/>
          <Route path='/admin/pupils' element={<Pupil/>}/>
          <Route path='/admin/teachers' element={<Teacher/>}/>
          <Route path='*' element={<NotFound/>}/>
        </Routes>
      </Provider>
  );
}

export default App;
