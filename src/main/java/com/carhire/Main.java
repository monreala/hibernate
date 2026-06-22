package com.carhire;

import com.carhire.dao.GenericDao;
import com.carhire.dao.GenericDaoImpl;
import com.carhire.model.Booking;
import com.carhire.model.Customer;
import com.carhire.model.Vehicle;
import com.carhire.model.VehicleCategory;
import com.carhire.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск Hibernate и тестирование CRUD ===");

        GenericDao<Customer, String> customerDao = new GenericDaoImpl<>();
        GenericDao<Vehicle, String> vehicleDao = new GenericDaoImpl<>();
        GenericDao<Booking, String> bookingDao = new GenericDaoImpl<>();

        try {
            System.out.println("\n--- [CREATE] Создаем новые записи ---");
            Customer customer = new Customer("C-1", "Иван Иванов", 30, 5);
            Vehicle vehicle = new Vehicle("A111AA", "BMW", "X5", VehicleCategory.SUV, 150.0, 15000);

            customerDao.save(customer);
            vehicleDao.save(vehicle);
            System.out.println("Клиент и машина успешно сохранены");

            Booking booking = new Booking("B-1", customer, vehicle, 5);
            bookingDao.save(booking);
            System.out.println("Бронирование B-1 сохранено");

            System.out.println("\n--- [READ] Читаем данные из базы ---");
            Booking savedBooking = bookingDao.findById(Booking.class, "B-1");
            System.out.println("Найдено бронирование: Клиент = " + savedBooking.getCustomer().getFullName() +
                    ", Авто = " + savedBooking.getVehicle().getBrand());

            List<Customer> allCustomers = customerDao.findAll(Customer.class);
            System.out.println("Всего клиентов в базе: " + allCustomers.size());

            System.out.println("\n--- [UPDATE] Обновляем бронирование ---");
            savedBooking.confirm();
            bookingDao.update(savedBooking);

            Booking updatedBooking = bookingDao.findById(Booking.class, "B-1");
            System.out.println("Новый статус бронирования: " + updatedBooking.getStatus());

            System.out.println("\n--- [DELETE] Удаляем записи ---");
            bookingDao.delete(updatedBooking);
            customerDao.delete(customer);
            vehicleDao.delete(vehicle);
            System.out.println("Все тестовые записи удалены. База чистая!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
            System.out.println("\n=== Приложение завершило работу ===");
        }
    }
}