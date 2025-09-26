import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login';
import Register from './pages/Register/Register';
import 'bootstrap/dist/css/bootstrap.min.css';
import StudentProfile from './pages/Student/StudentProfile';
import StudentList from './pages/Admin/StudentList';
function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/studentprofile" element={<StudentProfile />} />
        <Route path="/studentlist" element={<StudentList />} />
      </Routes>
    </>
  );
}

export default App;
