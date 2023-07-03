package edu.hit.bailexi.service;

public interface FavoriteService {
    void add(String rid, int uid);

    Boolean isFavourite(String rid, int uid);

    void removeFavourite(String rid, int uid);
}