package dao;

import model.Show;

import java.util.List;

public interface ShowDAO extends GenericDAO<Show, String> {
    List<Show> listShowsByCurrentDateAndDirector(String director);
}
