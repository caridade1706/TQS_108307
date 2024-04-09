import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import { BrowserRouter } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.css'
import { CurrencyProvider } from './components/CurrencyContext.tsx'


ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <CurrencyProvider> {/* Envolve o App com o CurrencyProvider */}
        <App />
      </CurrencyProvider>
    </BrowserRouter>
  </React.StrictMode>,  
)
