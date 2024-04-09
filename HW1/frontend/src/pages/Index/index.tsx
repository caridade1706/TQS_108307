import { useState } from 'react';
import './index.css';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import { useNavigate } from 'react-router-dom';

const Index = () => {
  const navigate = useNavigate();

  const [selectedDeparture, setSelectedDeparture] = useState('');
  const [selectedDestination, setSelectedDestination] = useState('');
  const [departureDate, setDepartureDate] = useState('');

  const handleFindRoutes = () => {
    console.log("Finding routes");
    if (selectedDeparture && selectedDestination && departureDate) {
      navigate('/api/routes', { state: { from: selectedDeparture, to: selectedDestination, date: departureDate }});
    } else {
      console.log("Please fill in all fields.");
    }
  }

  return (
    <div>
      <Navbar />
      <div className="container" style={{ marginTop: "50px" }}>
        <h1>Welcome to the Bus Ticketing Service!</h1>
        <p>This is the TQS Homework!</p>
        <p>Check out your next trip!</p>
        <div className="dropdown-container">
          <input
            type="text"
            placeholder="Type your departure city:"
            className="dropdown" // Reutilizando a classe dropdown para manter o estilo
            id='origin'
            name='origin'
            value={selectedDeparture}
            onChange={(e) => setSelectedDeparture(e.target.value)}
          />
        
          <input
            type="text"
            placeholder="Type your destination city:"
            className="dropdown" // Reutilizando a classe dropdown para manter o estilo
            id='destination'
            name='destination'
            value={selectedDestination}
            onChange={(e) => setSelectedDestination(e.target.value)}
          />
          
          <input
            type="date"
            className="date-picker"
            name='date'
            value={departureDate}
            onChange={(e) => setDepartureDate(e.target.value)}
          />
          <button className="find-routes-btn" type='button' onClick={handleFindRoutes}>Find Routes</button>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Index;
