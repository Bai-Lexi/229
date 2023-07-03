package edu.hit.bailexi.dao;

import edu.hit.bailexi.domain.Favorite;

public interface FavoriteDao {
    void add(String rid, int uid);


    Favorite findFavouriteByRidAndUid(String rid, int uid);

    int findFavoriteCount(String rid);

    void removeFavourite(String rid, int uid);
}
