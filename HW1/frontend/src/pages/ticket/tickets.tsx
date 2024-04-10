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
                        <p><strong>Bus:</strong> {reservationDetails.routeTicket.busPlate}</p>
                        <p><strong>Origin:</strong> {reservationDetails.routeTicket.origin}</p>
                        <p><strong>Destination:</strong> {reservationDetails.routeTicket.destination}</p>
                        <p><strong>Date:</strong> {reservationDetails.routeTicket.date}</p>
                        <p><strong>Departure Time:</strong> {reservationDetails.routeTicket.time}</p>
                        <p><strong>Arrival Time:</strong> {reservationDetails.routeTicket.timeDestination}</p>
                        <p><strong>Duration:</strong> {reservationDetails.routeTicket.duration} hours</p>
                        <p><strong>Price:</strong> {((reservationDetails.routeTicket.price * exchangeRate).toFixed(2))} {selectedCurrency}</p>
                        <p><strong>Occupation:</strong> {reservationDetails.routeTicket.ocupation} available seats</p>
                        <h2>Personal Information</h2>
                        <p><strong>Name:</strong> {reservationDetails.personTicket.namePerson}</p>
                        <p><strong>Email:</strong> {reservationDetails.personTicket.emailPerson}</p>
                        <p><strong>Phone:</strong> {reservationDetails.personTicket.phonePerson}</p>
                        <p><strong>Address:</strong> {reservationDetails.personTicket.addressPerson}</p>
                        <p><strong>Zip:</strong> {reservationDetails.personTicket.zipPerson}</p>
                        <p><strong>City:</strong> {reservationDetails.personTicket.cityPerson}</p>
                        <h2>Payment Information</h2>
                        <p><strong>Card Type:</strong> {reservationDetails.cardTypeTicket}</p>
                        <p><strong>Card Number:</strong> {reservationDetails.cardNumberTicket}</p>
                        <p><strong>Card Year:</strong> {reservationDetails.cardYearTicket}</p>
                        <p><strong>Card Month:</strong> {reservationDetails.cardMonthTicket}</p>
                                     
                    
                    </div>
                )}
            </div>
            <Footer />
        </div>
    );
};

export default Tickets;
