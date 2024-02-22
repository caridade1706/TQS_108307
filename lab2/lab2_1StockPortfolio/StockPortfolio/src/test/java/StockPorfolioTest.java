import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.com.IStockmarketService;
import org.com.Stock;
import org.com.StocksPortfolio;


@ExtendWith(MockitoExtension.class)
public class StockPorfolioTest {

    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;


    @BeforeEach
    public void setUp() {
       portfolio = new StocksPortfolio(market);
    }

    @Test
    void getTotalValue(){

        portfolio.addStock(new Stock("NEI", 2));
        portfolio.addStock(new Stock("DETI", 4));

        when(market.lookUpPrice("NEI")).thenReturn(4.8);
        when(market.lookUpPrice("DETI")).thenReturn(1.5);

        double total = 4*1.5+2*4.8;

        assertThat(portfolio.getTotalValue(), is(total));
        verify(market, times(2)).lookUpPrice(anyString());
    }
    
}
