package Dao;



import entity.Favorites;

import java.util.List;

public interface FavoritesDao {
    List<Favorites> findAll();
    Favorites findById(int id);
    void create (Favorites item);

    void update (Favorites item);
    void deleteById(int id);
}
