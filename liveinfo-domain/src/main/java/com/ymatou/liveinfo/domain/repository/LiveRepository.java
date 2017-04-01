package com.ymatou.liveinfo.domain.repository;

import com.mongodb.MongoClient;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.domain.service.LiveService;
import com.ymatou.liveinfo.infrastructure.mongodb.MongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveRepository extends MongoRepository {

    @Resource(name = "productMongoClient")
    private MongoClient mongoClient;

    private final String dbName = "YmtProducts";

    /**
     * 获取到MongoClient
     *
     * @return
     */
    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     * 获取买手直播中的商品
     * @param sellerId
     * @return
     */
    public Live getSellerCurrentLive(int sellerId){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);

        query.and(
            query.criteria("sid").equal(sellerId),
            query.criteria("action").equal(1)
        );

        return query.limit(1).get();
    }
}
