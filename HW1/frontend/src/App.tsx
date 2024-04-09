import {Routes, Route } from 'react-router-dom'
import Index from './pages/Index/index'
import Bus from './pages/Bus/bus'
import Reservation from './pages/reservation/reservation'
import Ticket from './pages/ticket/tickets'

function App() {

  return (

    <Routes>
      <Route path='/' element={<Index />} />
      <Route path='/api/routes' element={<Bus />} /> 
      <Route path='/api/routeselected' element={<Reservation />} /> 
      <Route path='/api/ticket' element={<Ticket />} /> 


    </Routes>


    
  )
}

export default App
