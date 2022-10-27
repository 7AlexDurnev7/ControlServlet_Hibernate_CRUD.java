package DAO;

import CarEntity.AutoEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

class CarDAOTest {

    @Test
    void insertAuto() {
            AutoEntity car = new AutoEntity();
            car.setManufacturer("Шишига");
            car.setModel("133");
            car.setYear(1990);
            car.setPrice(77000.0);
            new CarDAO().insertAuto(car);
    }

    @Test
    void getAutoById() {
        AutoEntity result = new CarDAO().getAutoById(1L);
        System.out.println(result);
    }

    @Test
    void getAllAuto() {
        List<AutoEntity> cars = new CarDAO().getAllAuto();
        for (AutoEntity car : cars) {
            System.out.println(car);
        }
    }

    @Test
    void updateAuto() {
        AutoEntity result = new CarDAO().getAutoById(2L);
        result.setYear(2000);
        System.out.println(result);
    }

    @Test
    void deleteAutoById() {
        new CarDAO().deleteAutoById(3L);
    }

    @Test
    void getStartsWith () {
        List<AutoEntity> cars = new CarDAO().getStartsWith("Ш");
        for (AutoEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // получить все автомобили, младше какого-либо года года выпуска
    @Test
    void getAllCarsYoungerThan() {
        List<AutoEntity> cars = new CarDAO().getAllCarsYoungerThan(1945);
        for (AutoEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // получаем авто дешевле какой-либо суммы
    @Test
    void getAllCarsCheaperThan() {
        List<AutoEntity> cars = new CarDAO().getAllCarsCheaperThan(200000);
        for (AutoEntity auto : cars) {
            System.out.println(auto);
        }
    }
}