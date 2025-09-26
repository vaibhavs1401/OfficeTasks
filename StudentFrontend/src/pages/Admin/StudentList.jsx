import React, { useState } from 'react';

function StudentList() {
  const [students, setStudents] = useState([
    { id: 1, name: 'Dhruv', age: 20, std: '12', email: 'dhruv@gamil.com', rollNo: '101' },
    { id: 2, name: 'Kunal', age: 19, std: '11', email: 'kunal@gmail.com', rollNo: '102' },
  ]);

  const initialFormState = { id: null, name: '', age: '', std: '', email: '', rollNo: '' };
  const [editingStudent, setEditingStudent] = useState(null);
  const [form, setForm] = useState(initialFormState);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleAdd = (e) => {
    e.preventDefault();
    if (!form.name || !form.age || !form.std || !form.email || !form.rollNo) return;

    if (editingStudent) {
      // Update existing
      setStudents(
        students.map((s) => (s.id === editingStudent.id ? { ...form, id: editingStudent.id } : s))
      );
      setEditingStudent(null);
    } else {
      // Add new
      const newStudent = { ...form, id: Date.now() };
      setStudents([...students, newStudent]);
    }
    setForm(initialFormState);
  };

  const handleEditClick = (student) => {
    setEditingStudent(student);
    setForm(student);
  };

  const handleDeleteClick = (id) => {
    setStudents(students.filter((s) => s.id !== id));
    if (editingStudent && editingStudent.id === id) {
      setEditingStudent(null);
      setForm(initialFormState);
    }
  };

  const handleCancelEdit = () => {
    setEditingStudent(null);
    setForm(initialFormState);
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Student List</h2>

      <form onSubmit={handleAdd} className="mb-4">
        <div className="row g-2">
          <div className="col-md-2">
            <input
              name="rollNo"
              type="text"
              placeholder="Roll No"
              className="form-control"
              value={form.rollNo}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="col-md-3">
            <input
              name="name"
              type="text"
              placeholder="Name"
              className="form-control"
              value={form.name}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="col-md-1">
            <input
              name="age"
              type="number"
              placeholder="Age"
              className="form-control"
              value={form.age}
              onChange={handleInputChange}
              required
              min="0"
            />
          </div>
          <div className="col-md-3">
            <input
              name="std"
              type="text"
              placeholder="Class"
              className="form-control"
              value={form.std}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="col-md-3">
            <input
              name="email"
              type="email"
              placeholder="Email"
              className="form-control"
              value={form.email}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>

        <div className="mt-3">
          <button type="submit" className="btn btn-success me-2">
            {editingStudent ? 'Update Student' : 'Add Student'}
          </button>
          {editingStudent && (
            <button type="button" className="btn btn-secondary" onClick={handleCancelEdit}>
              Cancel
            </button>
          )}
        </div>
      </form>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>Roll No</th>
            <th>Name</th>
            <th>Age</th>
            <th>Class</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {students.length === 0 && (
            <tr>
              <td colSpan="6" className="text-center">
                No students available
              </td>
            </tr>
          )}
          {students.map((student) => (
            <tr key={student.id}>
              <td>{student.rollNo}</td>
              <td>{student.name}</td>
              <td>{student.age}</td>
              <td>{student.std}</td>
              <td>{student.email}</td>
              <td>
                <button className="btn btn-primary btn-sm me-2" onClick={() => handleEditClick(student)}>
                  Edit
                </button>
                <button className="btn btn-danger btn-sm" onClick={() => handleDeleteClick(student.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentList;
