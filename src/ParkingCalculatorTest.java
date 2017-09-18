import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParkingCalculatorTest extends ParkingCalculator {

    @Test
    public void verificarMudancaDia() throws Exception {
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 1 Minutes)";

        driver.get(URL);
        calcularParking(
                "Short-Term Parking",
                "11:59",
                "PM",
                "00:00",
                "AM",
                "01/01/2020",
                "01/02/2020"
        );

        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);
    }


    @Test
    public void sairAntesDeEntrar() throws Exception {
        String mensagemEsperada = "ERROR! YOUR EXIT DATE OR TIME IS BEFORE YOUR ENTRY DATE OR TIME";

        driver.get(URL);
        calcularParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "09:00",
                "AM",
                dataFutura(),
                dataAtual()
        );

        String mensagemAtual = getMessage();

        assertEquals(mensagemEsperada, mensagemAtual);
    }
}