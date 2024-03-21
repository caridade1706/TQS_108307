package tqs;


import tqs.euromillions.CuponEuromillions;
import tqs.euromillions.Dip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * demonstrates a client for random euromillions bets
 */
class DemoMain {
    static final Logger log = LoggerFactory.getLogger(lookup().lookupClass());

    public static void main(String[] args) {

        // simulate a coupon with three user bets
        CuponEuromillions myBet = new CuponEuromillions();
        myBet.appendDip(Dip.generateRandomDip());
        myBet.appendDip(Dip.generateRandomDip());
        myBet.appendDip(Dip.generateRandomDip());


    }
}
