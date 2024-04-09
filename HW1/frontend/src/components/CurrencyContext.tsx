// src/contexts/CurrencyContext.js
import { createContext, useContext, useState } from 'react';

const CurrencyContext = createContext<any>(null);

export const useCurrency = () => useContext(CurrencyContext);

import { ReactNode } from 'react';

export const CurrencyProvider = ({ children }: { children: ReactNode }) => {
  // Definindo "EUR" como valor inicial para selectedCurrency
  const [selectedCurrency, setSelectedCurrency] = useState("EUR");

  const handleCurrencyChange = (currency: string) => {
    setSelectedCurrency(currency);
  };

  return (
    <CurrencyContext.Provider value={{ selectedCurrency, handleCurrencyChange }}>
      {children}
    </CurrencyContext.Provider>
  );
};
