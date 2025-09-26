import React, { useState } from 'react';

function StudentProfile() {
  const [profile, setProfile] = useState({
    name: 'Vaibhav Shinde',
    age: 20,
    std: '12th Grade',
    email: 'vaibhavshinde1401@gamail.com',
  });

  const [isEditing, setIsEditing] = useState(false);
  const [editableProfile, setEditableProfile] = useState(profile);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditableProfile(prev => ({ ...prev, [name]: value }));
  };

  const handleEditToggle = () => {
    setEditableProfile(profile); // Reset edits on toggle
    setIsEditing(!isEditing);
  };

  const handleSave = () => {
    setProfile(editableProfile);
    setIsEditing(false);
  };

  return (
    <div className="container mt-5" style={{ maxWidth: '500px' }}>
      <h2 className="mb-4 text-center">Student Profile</h2>

      {!isEditing ? (
        <div>
          <p><strong>Name:</strong> {profile.name}</p>
          <p><strong>Age:</strong> {profile.age}</p>
          <p><strong>Class:</strong> {profile.std}</p>
          <p><strong>Email:</strong> {profile.email}</p>

          <button className="btn btn-primary" onClick={handleEditToggle}>
            Edit
          </button>
        </div>
      ) : (
        <form onSubmit={e => { e.preventDefault(); handleSave(); }}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">Name</label>
            <input
              id="name"
              name="name"
              type="text"
              className="form-control"
              value={editableProfile.name}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="age" className="form-label">Age</label>
            <input
              id="age"
              name="age"
              type="number"
              className="form-control"
              value={editableProfile.age}
              onChange={handleChange}
              required
              min="0"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="std" className="form-label">Class</label>
            <input
              id="std"
              name="std"
              type="text"
              className="form-control"
              value={editableProfile.std}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input
              id="email"
              name="email"
              type="email"
              className="form-control"
              value={editableProfile.email}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="btn btn-success me-2">Save</button>
          <button type="button" className="btn btn-secondary" onClick={handleEditToggle}>
            Cancel
          </button>
        </form>
      )}
    </div>
  );
}

export default StudentProfile;
