package DAO;

import CarEntity.AutoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class CarDAO implements IControlOrder {

    @Override
    public void insertAuto(AutoEntity order) {
        // 1. создаем фабрику
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        // 2. manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 3. объект транзакции
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // выполнение самой операции создания записи в БД
            transaction.begin();
            entityManager.persist(order);   // создание новой записи на основе объекта
            transaction.commit();
        } finally {
            // все закрыть и откатить транзакцию, если нужно
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public AutoEntity getAutoById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // переменная для хранения объекта-результата поиска
        AutoEntity result = null;
        try {
            transaction.begin();
            // сама операция
            result = entityManager.find(AutoEntity.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return result;  // вернуть результат
    }

    @Override
    public List<AutoEntity> getAllAuto() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<AutoEntity> auto = null;

        try {
            transaction.begin();
            // сама операция
            auto = entityManager.createQuery("SELECT e FROM AutoEntity e").getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return auto;
    }

    @Override
    public void updateAuto(AutoEntity car) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // 1. получаю обновляемый объект по Id
            AutoEntity updated = entityManager.find(AutoEntity.class, car.getId());
            // 2. обновляю поля обновляемого объекта
            updated.updateFields(car);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void deleteAutoById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            // 1. получить удаляемый объект
            AutoEntity deleted = entityManager.find(AutoEntity.class, id);
            // 2. удалить
            entityManager.remove(deleted);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public List<AutoEntity> getStartsWith(String s) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<AutoEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createQuery("SELECT e FROM AutoEntity e where e.manufacturer " +
                            "like :name", AutoEntity.class)
                    .setParameter("name", s + "%")
                    .getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return cars;
    }

    @Override
    public List<AutoEntity> getAllCarsYoungerThan(int year) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<AutoEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createNamedQuery("get_all_cars_younger_than", AutoEntity.class)
                    .setParameter("param", year).getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return cars;
    }
    @Override
    public List<AutoEntity> getAllCarsCheaperThan(double price) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<AutoEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createNamedQuery("get_all_cars_cheaper_than", AutoEntity.class)
                    .setParameter("param", price).getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return cars;
    }
}
