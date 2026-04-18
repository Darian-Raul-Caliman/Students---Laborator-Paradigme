import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import ro.ulbs.proiectaresoftware.students.AplicatieCuBursa;
import ro.ulbs.proiectaresoftware.students.StudentBursier;

public class AplicatieCuBursaTest {

    private AplicatieCuBursa appCuBursa;

    @BeforeEach
    public void setup() {
        appCuBursa = new AplicatieCuBursa();
    }
    @Test
    public void sortTest1() {

        List<StudentBursier> lista = appCuBursa.genereaza();


        List<StudentBursier> sortata = appCuBursa.sorteaza(lista);


        Assertions.assertEquals(5, sortata.size());


        Assertions.assertEquals("Ioan", sortata.get(0).getPrenume());
        Assertions.assertEquals("ISM141/1", sortata.get(0).getFormațieDeStudiu());

        Assertions.assertEquals("Andrei", sortata.get(1).getPrenume());
        Assertions.assertEquals("ISM141/2", sortata.get(1).getFormațieDeStudiu());

        Assertions.assertEquals("Anamaria", sortata.get(2).getPrenume());
        Assertions.assertEquals("TI131/1", sortata.get(2).getFormațieDeStudiu());


        Assertions.assertEquals("Bianca", sortata.get(3).getPrenume());
        Assertions.assertEquals(100.00, sortata.get(3).getCuantumBursa(), 0.001);

        Assertions.assertEquals("Bianca", sortata.get(4).getPrenume());
        Assertions.assertEquals(780.80, sortata.get(4).getCuantumBursa(), 0.001);


        for (int i = 0; i < sortata.size() - 1; i++) {
            StudentBursier s1 = sortata.get(i);
            StudentBursier s2 = sortata.get(i + 1);


            int cmpFormatie = s1.getFormațieDeStudiu().compareTo(s2.getFormațieDeStudiu());
            Assertions.assertTrue(cmpFormatie <= 0, "Eroare: Formatia de studiu nu respecta ordinea alfabetica.");


            if (cmpFormatie == 0) {
                int cmpNume = s1.getNume().compareTo(s2.getNume());
                Assertions.assertTrue(cmpNume <= 0, "Eroare: Numele nu respecta ordinea alfabetica.");


                if (cmpNume == 0) {
                    int cmpPrenume = s1.getPrenume().compareTo(s2.getPrenume());
                    Assertions.assertTrue(cmpPrenume <= 0, "Eroare: Prenumele nu respecta ordinea alfabetica.");
                }
            }
        }
    }
}
