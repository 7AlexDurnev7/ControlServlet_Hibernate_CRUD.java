package DAO;

import CarEntity.AutoEntity;

import java.util.List;

public interface IControlOrder {
    void insertAuto(AutoEntity order); // добавить
    AutoEntity getAutoById(Long id); // получить  по id

    List<AutoEntity> getAllAuto(); //  получить все

    void updateAuto(AutoEntity car); // обновить данные

    void deleteAutoById(Long id);  // удалить по id

    List<AutoEntity> getStartsWith(String str); // получить автомобили, название которых начинаются с запрашиваемой буквы

    List<AutoEntity> getAllCarsYoungerThan (int year);

    List<AutoEntity> getAllCarsCheaperThan (double price);
}
