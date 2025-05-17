package dao;

import model.Order;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Map;

public interface OrderDAO extends GenericDAO<Order, Integer> {
    //  Calculate the total amount of all bills for a certain day.
    double totalBillAmountForDay(LocalDate day);

    //  Statistics of total bills by month/year.
    Map<String, Integer> totalBillsByMonth(Month month);
    Map<String, Integer> totalBillsByYear(Year year);
}
