import { useEffect, useState } from 'react';
import './ticket.css';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import { useCurrency } from '../../components/CurrencyContext';



const Tickets = () => {
    const [reservationId, setReservationId] = useState('');
    const [reservationDetails, setReservationDetails] = useState<any>();
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');
    const [exchangeRate, setExchangeRate] = useState(1); // Inicia com 1 por padrão
    const { selectedCurrency } = useCurrency() as { selectedCurrency: string };

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
      }, [selectedCurrency]); 
      
    

    const fetchReservationDetails = async () => {
        if (!reservationId) {
            alert('Please enter a reservation ID');
            return;
        }

        setIsLoading(true);
        setError('');

        try {
            // Substitua esta URL pelo endpoint real de onde você busca os detalhes da reserva
            const response = await fetch(`http://localhost:8080/api/tickets/reservation/${reservationId}`);

            if (!response.ok) {
                throw new Error('Failed to fetch reservation details');
            }

            const data = await response.json();
            console.log(data);
            setReservationDetails(data);
        } catch (err) {
            setError('Failed to fetch reservation details');
            console.error(err);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div>
            <Navbar />
            <div className="container" style={{marginBottom: "50px"}}>
                <div className="reservation-id-input">
                    <label htmlFor="reservationId">Enter Reservation ID:</label>
                    <input
                        type="text"
                        id="reservationId"
                        value={reservationId}
                        onChange={(e) => setReservationId(e.target.value)}
                    />
                    <button onClick={fetchReservationDetails} disabled={isLoading}>
                        {isLoading ? 'Loading...' : 'Find Reservation'}
                    </button>
                </div>

                {error && <p className="error-message">{error}</p>}

                {reservationDetails && (
                    <div className="reservation-details"  style={{marginTop: "20px"}}>
                        <h2>Reservation Details</h2>
                        <p><strong>Bus:</strong> {reservationDetails.route.plateBus}</p>
                        <p><strong>Origin:</strong> {reservationDetails.route.origin}</p>
                        <p><strong>Destination:</strong> {reservationDetails.route.destination}</p>
                        <p><strong>Date:</strong> {reservationDetails.route.date}</p>
                        <p><strong>Departure Time:</strong> {reservationDetails.route.time}</p>
                        <p><strong>Arrival Time:</strong> {reservationDetails.route.timeDestination}</p>
                        <p><strong>Duration:</strong> {reservationDetails.route.duration} hours</p>
                        <p><strong>Price:</strong> {((reservationDetails.route.price * exchangeRate).toFixed(2))} {selectedCurrency}</p>
                        <p><strong>Occupation:</strong> {reservationDetails.route.ocupation} available seats</p>
                        <h2>Personal Information</h2>
                        <p><strong>Name:</strong> {reservationDetails.person.name}</p>
                        <p><strong>Email:</strong> {reservationDetails.person.email}</p>
                        <p><strong>Phone:</strong> {reservationDetails.person.phone}</p>
                        <p><strong>Address:</strong> {reservationDetails.person.address}</p>
                        <p><strong>Zip:</strong> {reservationDetails.person.zip}</p>
                        <p><strong>City:</strong> {reservationDetails.person.city}</p>
                        <h2>Payment Information</h2>
                        <p><strong>Card Type:</strong> {reservationDetails.cardType}</p>
                        <p><strong>Card Number:</strong> {reservationDetails.cardNumber}</p>
                        <p><strong>Card Year:</strong> {reservationDetails.cardYear}</p>
                        <p><strong>Card Month:</strong> {reservationDetails.cardMonth}</p>
                                     
                    
                    </div>
                )}
            </div>
            <Footer />
        </div>
    );
};

export default Tickets;
