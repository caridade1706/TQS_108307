import { useEffect, useState } from 'react';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import { useLocation, useNavigate } from 'react-router-dom';
import { useCurrency } from '../../components/CurrencyContext';

interface RouteDetails {
  id: number;
  busPlate: string;
  origin: string;
  destination: string;
  date: string;
  time: string;
  timeDestination: string;
  duration: number;
  price: number;
  occupation: number;
}

const reservation = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [exchangeRate, setExchangeRate] = useState(1); // Inicia com 1 por padrão
  const { selectedCurrency } = useCurrency() as { selectedCurrency: string };

  // Estado inicial com valores vazios ou padrões
  const initialState: RouteDetails = {
    id: 0,
    busPlate: '',
    origin: '',
    destination: '',
    date: '',
    time: '',
    timeDestination: '',
    duration: 0,
    price: 0,
    ocupation: 0
  };

  const [routeDetails, setRouteDetails] = useState<RouteDetails>(initialState);

  const getExchangeRate = async (toCurrency: string) => {
    try {
        const response = await fetch(`http://localhost:8080/api/exchange-rate/all`);
        const data = await response.json();
        // Assumindo que a resposta inclui uma estrutura como { "EUR": { "USD": 1.1, "GBP": 0.9 } }
        // e você quer a taxa para a moeda toCurrency a partir do EUR.
        const rate = data["EUR"][toCurrency]; // Ajuste baseado na estrutura real da sua API
        return rate;
    } catch (error) {
        console.error("Erro ao obter a taxa de câmbio:", error);
        return 1; // Retorna 1 como padrão para evitar multiplicação por undefined/null
    }
  };
  useEffect(() => {
    // Função para atualizar a taxa de câmbio
    const updateExchangeRate = async () => {
      const rate = await getExchangeRate(selectedCurrency);
      setExchangeRate(rate);
    };
  
    updateExchangeRate();
  }, [selectedCurrency]); // Dependência: atualiza sempre que a moeda selecionada muda
  
  const priceInSelectedCurrency =  (routeDetails.price * exchangeRate).toFixed(2);

  useEffect(() => {
    // Supondo que o ID da rota seja passado via estado do location
    const routeId = location.state?.routeid;
    if (routeId) {
      fetch(`http://localhost:8080/api/routes/route/${routeId}`)
        .then(response => response.json())
        .then(data => {
          setRouteDetails(data);
        })
        .catch(error => console.error("Erro ao buscar dados da rota", error));
    }
  }, [location.state]);
  const [formData, setFormData] = useState({
    routeid:0,
    name: '',
    email: '',
    phone: '',
    address: '',
    city: '',
    zip: '',
    cardType: 'Visa',
    cardNumber: '',
    cardMonth: '',
    cardYear: '',
    cardCvv: ''
  });

  const handleInputChange = (e: { target: { name: any; value: any; }; }) => {
    const { name, value } = e.target;
    setFormData(prevState => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    formData.routeid = routeDetails.id;
    console.log(formData);
    console.log(JSON.stringify(formData));
    fetch('http://localhost:8080/api/tickets/createreservation', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
      .then(response => response.json())
      .then(data => {
        alert('Ticket purchased successfully \nID TICKET: ' + data.ticket);
        console.log(data);
      })
      .catch(error => {
        alert('Failed to purchase ticket' );
        console.error('Failed to purchase ticket', error);
      }); 
    
    navigate('/');
  };

  if (!routeDetails) {
    return <div>Carregando detalhes da rota...</div>;
  }
  
  return (
    <div>
      <Navbar />
      <div className="container" style={{ marginBottom: "50px" }}>
        <h1>Complete your Bus Reservation</h1>
        <div className="reservation-details">
          <h2>Route Details</h2>
          <p><strong>Bus:</strong> {routeDetails.busPlate}</p>
          <p><strong>Origin: </strong> {routeDetails.origin}</p>
          <p><strong>Destination: </strong> {routeDetails.destination}</p>
          <p><strong>Date: </strong> {routeDetails.date}</p>
          <p><strong>Departure Time: </strong> {routeDetails.time}</p>
          <p><strong>Arrival Time: </strong> {routeDetails.timeDestination}</p>
          <p><strong>Duration: </strong> {routeDetails.duration} hours</p>
          <p><strong>Price: </strong> {priceInSelectedCurrency} {selectedCurrency}</p>
          <p><strong>Occupation: </strong> {routeDetails.ocupation} available seats</p>
        </div>
        <form className="purchase-form" onSubmit={handleSubmit}>
          <h2>Please submit the form below to purchase the Ticket.</h2>

          {/* Name Input */}
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
            required
          />

          {/* Email Input */}
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            required
          />

          {/* Phone Input */}
          <label htmlFor="phone">Phone</label>
          <input
            type="tel"
            id="phone"
            name="phone"
            value={formData.phone}
            onChange={handleInputChange}
            required
          />


          {/* Rest of the form inputs... */}
          {/* Address Input */}
          <label htmlFor="address">Address</label>
          <input
            type="text"
            id="address"
            name="address"
            value={formData.address}
            onChange={handleInputChange}
            required
          />

          {/* City Input */}
          <label htmlFor="city">City</label>
          <input
            type="text"
            id="city"
            name="city"
            value={formData.city}
            onChange={handleInputChange}
            required
          />

          {/* ZIP Input */}
          <label htmlFor="zip">ZIP</label>
          <input
            type="text"
            id="zip"
            name="zip"
            value={formData.zip}
            onChange={handleInputChange}
            required
          />
          <label htmlFor="cardType">Card Type</label>
          <select
            id="cardType"
            name="cardType"
            value={formData.cardType}
            onChange={handleInputChange}
            required
          >
            <option value="Visa">Visa</option>
            <option value="MasterCard">MasterCard</option>
            <option value="American Express">American Express</option>
          </select>

          {/* Card Number Input */}
          <label htmlFor="cardNumber">Card Number</label>
          <input
            type="text"
            id="cardNumber"
            name="cardNumber"
            value={formData.cardNumber}
            onChange={handleInputChange}
            required
          />

          {/* Card Month Input */}
          <label htmlFor="cardMonth">Card Month</label>
          <input
            type="text"
            id="cardMonth"
            name="cardMonth"
            value={formData.cardMonth}
            onChange={handleInputChange}
            required
          />

          {/* Card Year Input */}
          <label htmlFor="cardYear">Card Year</label>
          <input
            type="text"
            id="cardYear"
            name="cardYear"
            value={formData.cardYear}
            onChange={handleInputChange}
            required
          />

          {/* Card CVC Input */}
          <label htmlFor="cardCvv">Card CVC</label>
          <input
            type="text"
            id="cardCvv"
            name="cardCvv"
            value={formData.cardCvv}
            onChange={handleInputChange}
            required
          />

          {/* Submit Button */}
          <button id='purchaseButton' type="submit">Purchase Ticket</button>
        </form>
      </div>
      <Footer />
    </div>
  );
};


export default reservation
