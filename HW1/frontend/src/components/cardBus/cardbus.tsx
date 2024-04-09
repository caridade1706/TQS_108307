import React from 'react'
import './cardbus.css'
import { useNavigate } from 'react-router-dom';
import { useCurrency } from '../CurrencyContext';

interface Bus {
  routeId: number;
  origin: string;
  destination: string;
  date: string;
  time: string;
  timeDestination: string;
  duration: number;
  price: number;
  ocupation: number;
  busPlate: string;
  
}


interface CardBusProps {
  buttonId: any;
  bus: Bus;
}


const  cardbus: React.FC<CardBusProps> = ({ bus, buttonId }) => {

  const navigate = useNavigate();
  const { selectedCurrency } = useCurrency() as { selectedCurrency: string };

  const handleFindRoute = () => {
    console.log("Finding route");
    console.log(bus);
    console.log(bus.routeId);
    if (bus.routeId) {
      navigate('/api/routeselected', { state: {routeid: bus.routeId }});
    } else {
      console.log("Please select route");
    }
  }

  return (
    <div className="bus-card">
      <div className="bus-timings">
        <div className="bus-time">
          <span className="departure-time">{bus.time}</span>
          <span className="bus-origin">{bus.origin}</span>
        </div>
        <div className="bus-line-container">
          <div className="bus-line" />
          <span className="bus-duration">{bus.duration}h</span>
          <span className="bus-direct">direct</span>
        </div>
        <div className="bus-time">
          <span className="arrival-time">{bus.timeDestination}</span>
          <span className="bus-destination">{bus.destination}</span>
        </div>
      </div>
      <div className="bus-booking">
        <div className="bus-class-price">
        <span className="price">{bus.price.toFixed(2)} {selectedCurrency}</span>
          <span className="date">{bus.date}</span>
          <span className="plate">Bus: {bus.busPlate}</span>
          
        </div>
      </div>
      <div className="bus-booking">
        <button id={buttonId} className="booking-button" onClick={handleFindRoute}>Booking</button>
      </div>
    </div>
  )
}

export default cardbus
