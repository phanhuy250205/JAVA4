package Dao;


import entity.Videos;

import java.util.List;

public interface Videosdao {

    List<Videos> findAll();
    Videos findById(String id);
    void create (Videos item);

    void update (Videos item);
    void deleteById(int id);
}
