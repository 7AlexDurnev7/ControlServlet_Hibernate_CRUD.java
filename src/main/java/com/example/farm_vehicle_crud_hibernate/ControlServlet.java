package com.example.farm_vehicle_crud_hibernate;

import CarEntity.AutoEntity;
import DAO.CarDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarDAO carDAO;

    @Override
    public void init() {
        carDAO = new CarDAO();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCar(request, response);
                    break;
                case "/delete":
                    deleteCar(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCar(request, response);
                    break;
                default:
                    listCars(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<AutoEntity> listCars = carDAO.getAllAuto();
        request.setAttribute("listCars", listCars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
// int id = Integer.parseInt(request.getParameter("id"));
        Long id = Long.parseLong(request.getParameter("id"));
        AutoEntity existingCar = carDAO.getAutoById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        request.setAttribute("car", existingCar);
        dispatcher.forward(request, response);
    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String manufacturer = request.getParameter("nameCar");
        String model = request.getParameter("modelF");
        Integer year = Integer.valueOf(request.getParameter("yearF"));
        Double price = Double.valueOf(request.getParameter("priceF"));

        AutoEntity newOrder = new AutoEntity();
        newOrder.setManufacturer(manufacturer);
        newOrder.setModel(model);
        newOrder.setYear(year);
        newOrder.setPrice(price);
        carDAO.insertAuto(newOrder);
        response.sendRedirect("list");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String manufacturer = request.getParameter("nameCar");
        String model = request.getParameter("modelF");
        Integer year = Integer.valueOf(request.getParameter("yearF"));
        Double price = Double.valueOf(request.getParameter("priceF"));

        AutoEntity newOrder = carDAO.getAutoById(id);
        newOrder.setManufacturer(manufacturer);
        newOrder.setModel(model);
        newOrder.setYear(year);
        newOrder.setPrice(price);
        carDAO.updateAuto(newOrder);
        response.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        carDAO.deleteAutoById(id);
        response.sendRedirect("list");
    }
}
