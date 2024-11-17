package Dao;

import entity.Share;

import java.util.List;

public interface ShareDao {
    List<Share> findAll();
    Share findById(int id);
    void create(Share share);
    void update(Share share);
    void deleteById(int id);
}
