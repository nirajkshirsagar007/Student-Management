import { useState, useEffect } from 'react';

function StudentList() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Use environment variable for the API URL, default to localhost for development
  const apiUrl = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/students';

  useEffect(() => {
    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        setStudents(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, [apiUrl]);

  if (loading) return <div style={{marginTop: '20px'}}>Loading students...</div>;
  if (error) return <div style={{marginTop: '20px', color: 'red'}}>Error: {error}</div>;

  return (
    <div className="student-list">
      <h2>Students</h2>

      {students.length === 0 ? (
        <p>No students found in the database.</p>
      ) : (
        students.map((student) => (
          <div key={student.id} style={{ border: '1px solid #ccc', margin: '10px 0', padding: '10px', borderRadius: '5px' }}>
            <h3 style={{ margin: '0 0 5px 0' }}>{student.name}</h3>
            <p style={{ margin: '0' }}>{student.email}</p>
          </div>
        ))
      )}
    </div>
  )
}

export default StudentList