  import { useEffect, useState } from 'react';
  import Navbar from '../../components/Navbar';
  import Footer from '../../components/Footer';
  import CardBus from '../../components/cardBus/cardbus';
  import { useLocation } from 'react-router-dom';XMLDocument
  import { useCurrency } from '../../components/CurrencyContext';

  const Bus = () => {
    const [busInfo, setBusInfo] = useState([]);
    const location = useLocation();
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
      const { from, to, date } = location.state || {};
      if (from && to) {
        fetch(`http://localhost:8080/api/routes/routes?origin=${from}&destination=${to}&date=${date}`)
          .then(response => response.json())
          .then(async data => {
            const exchangeRate = await getExchangeRate(selectedCurrency);
            const transformedRoutes = data.map((route: { id: any; origin: any; destination: any; date: any; time: any; timeDestination: any; duration: any; price: number; ocupation: any; busPlate: any; }) => ({
              routeId: route.id,
              origin: route.origin,
              destination: route.destination,
              date: route.date,
              time: route.time, 
              timeDestination: route.timeDestination,
              duration: route.duration,
              price: route.price * exchangeRate,
              occupation: route.ocupation,
              busPlate: route.busPlate
            }));
            setBusInfo(transformedRoutes);
          })
          .catch(error => console.error("Failed to fetch route info:", error));
      }
    }, [location.state, selectedCurrency]);

    

    return (
      <div>
        <Navbar />
        {busInfo.map((bus, index) => (
          <CardBus key={index} bus={bus} buttonId={`button-${index}`} />
        ))}
        <Footer />
      </div>
    );
  }

  export default Bus;
