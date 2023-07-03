package edu.hit.bailexi.service.impl;

import edu.hit.bailexi.dao.FavoriteDao;
import edu.hit.bailexi.dao.RouteDao;
import edu.hit.bailexi.dao.impl.FavoriteDaoImpl;
import edu.hit.bailexi.dao.impl.RouteDaoImpl;
import edu.hit.bailexi.domain.Favorite;
import edu.hit.bailexi.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao =new FavoriteDaoImpl();
    private RouteDao routeDao =  new RouteDaoImpl();

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(rid,uid);
        int count = favoriteDao.findFavoriteCount(rid);
        routeDao.updateFavoriteCount(rid,count);
    }

    @Override
    public Boolean isFavourite(String rid, int uid) {
        Favorite favorite = favoriteDao.findFavouriteByRidAndUid(rid,uid);
        Boolean flag=false;
        if (favorite!=null){
            flag = true;
        }else{
            flag=false;
        }
        return flag;
    }

    @Override
    public void removeFavourite(String rid, int uid) {
        favoriteDao.removeFavourite(rid, uid);
        int count = favoriteDao.findFavoriteCount(rid);
        routeDao.updateFavoriteCount(rid,count);
    }
}
