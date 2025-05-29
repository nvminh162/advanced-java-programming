package dao.impl;

import jakarta.persistence.EntityManager;
import model.Stock;

public class StockDAOImpl extends GenericDAOImpl<Stock, Integer> implements dao.StockDAO {
    public StockDAOImpl(Class<Stock> clazz) {
        super(clazz);
    }

    public StockDAOImpl(EntityManager em, Class<Stock> clazz) {
        super(em, clazz);
    }
}
