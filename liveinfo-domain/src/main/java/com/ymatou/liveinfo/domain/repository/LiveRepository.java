package com.ymatou.liveinfo.domain.repository;

import com.mongodb.MongoClient;
import com.ymatou.liveinfo.domain.model.Live;
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

    private final String[] liveFields = "lid,sid,confirm,sid,flag,title,vcover,vurl,name,pic,add,country,end,addr,start,content,action"
            .split(",");

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

        return query.retrievedFields(true, liveFields).disableValidation().limit(1).get();
    }

    /**
     * 插入直播数据
     * @param live
     */
    public void insertLive(Live live){
        insertEntiy(dbName, live);
    }

    /**
     * 获取到最大的直播号
     * @return
     */
    public int getMaxLiveId(){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        Live live = query.retrievedFields(true, "lid").order("-lid").disableValidation().limit(1).get();
        if(live == null){
            return 0;
        }else{
            return live.getActivityId();
        }
    }
}
