
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { useCurrency } from './CurrencyContext';


const Navbar = () => {
  const { selectedCurrency, handleCurrencyChange } = useCurrency(); // Corrigido aqui


  const navigate = useNavigate();

  const handleFindTicket = () => {
    navigate('/api/ticket');
  }

  // Função para atualizar a moeda selecionada


  const currencies = [
    { code: "USD", name: "Dólar Americano" },
    { code: "EUR", name: "Euro" },
    { code: "GBP", name: "Libra Esterlina" },
    { code: "JPY", name: "Iene Japonês" },
    { code: "CAD", name: "Dólar Canadense" },
    { code: "AUD", name: "Dólar Australiano" },
    { code: "CHF", name: "Franco Suíço" },
    { code: "SEK", name: "Coroa Sueca" },
    { code: "NOK", name: "Coroa Norueguesa" },
    { code: "CNY", name: "Yuan Chinês" },
    { code: "NZD", name: "Dólar Neozelandês" },
    { code: "MXN", name: "Peso Mexicano" },
    { code: "RUB", name: "Rublo Russo" },
    { code: "INR", name: "Rupia Indiana" },
    { code: "TRY", name: "Lira Turca" },
    { code: "KRW", name: "Won Sul-Coreano" },
    { code: "ZAR", name: "Rand Sul-Africano" },
    { code: "BRL", name: "Real Brasileiro" },
    { code: "PHP", name: "Peso Filipino" },
    // Adicione mais moedas conforme necessário
  ];

  return (
    <div>
      <nav className="navbar bg-dark border-bottom border-body" data-bs-theme="dark" style={{ height: "70px" }}>
        <div className="container-fluid">
          <a className="navbar-brand" href="/">Bus Ticketing Service</a>
          <ul className="nav justify-content">
            <li className="nav-item">
              <a className="nav-link text-white" onClick={handleFindTicket}>Tickets</a>
            </li>
            <li className="nav-item">
              <div className="dropdown">
                <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                  {selectedCurrency ? selectedCurrency : "Selecione uma moeda"} {/* Exibe a moeda selecionada ou um texto padrão */}
                </button>
                <ul className="dropdown-menu dropdown-menu-end">
                  {currencies.map(currency => (
                    <li key={currency.code}>
                      <button className="dropdown-item" onClick={() => handleCurrencyChange(currency.code)}>
                        {currency.code} - {currency.name}
                      </button>
                    </li>
                  ))}
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Navbar;