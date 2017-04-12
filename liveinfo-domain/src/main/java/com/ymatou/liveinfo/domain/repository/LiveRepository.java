package com.ymatou.liveinfo.domain.repository;

import com.mongodb.MongoClient;
import com.ymatou.liveinfo.domain.model.Live;
import com.ymatou.liveinfo.facade.enums.LiveActionEnum;
import com.ymatou.liveinfo.infrastructure.mongodb.MongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxudong on 2017/4/1.
 */
@Component
public class LiveRepository extends MongoRepository {

    @Resource(name = "productMongoClient")
    private MongoClient mongoClient;

    private final String dbName = "YmtProducts";

    private final String[] liveFields = "lid,sid,confirm,flag,title,vcover,vurl,name,pic,add,country,end,addr,start,content,action"
            .split(",");

    private final FindOptions findOptionsLimitOne = new FindOptions().limit(1);

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
     * 获取买手进行中的直播信息
     *
     * @param sellerId
     * @return
     */
    public Live getSellerCurrentLive(int sellerId){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        Date now = Calendar.getInstance().getTime();
        query.and(
                query.criteria("sid").equal(sellerId),
                query.criteria("start").lessThanOrEq(now),
                query.criteria("end").greaterThanOrEq(now),
                query.criteria("confirm").equal(true),
                query.criteria("action").equal(LiveActionEnum.Available.getCode())
        );
        return query.retrievedFields(true, liveFields).disableValidation().order("-lid").get(findOptionsLimitOne);
    }

    /**
     * 批量获取买手正在进行中的直播信息
     * @param sellerIds
     * @return
     */
    public List<Live> getSellerCurrentLiveList(List<Integer> sellerIds){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        Date now = Calendar.getInstance().getTime();
        query.and(
                query.criteria("sid").in(sellerIds),
                query.criteria("start").lessThanOrEq(now),
                query.criteria("end").greaterThanOrEq(now),
                query.criteria("confirm").equal(true),
                query.criteria("action").equal(LiveActionEnum.Available.getCode())
        );
        return query.retrievedFields(true, liveFields).disableValidation().asList();
    }

    /**
     * 批量获取正在进行中的直播信息
     * @param liveIds
     * @return
     */
    public List<Live> getInProgressLivesByIds(List<Integer> liveIds){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        Date now = Calendar.getInstance().getTime();
        query.and(
                query.criteria("lid").in(liveIds),
                query.criteria("action").equal(LiveActionEnum.Available.getCode())
        );
        return query.retrievedFields(true, liveFields).disableValidation().asList();
    }

    /**
     * 批量获取买手进行中的直播
     * @param sellerIds
     * @return
     */
    public List<Live> getSellerCurrentLiveIdList(List<Integer> sellerIds){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        Date now = Calendar.getInstance().getTime();
        query.and(
                query.criteria("sid").in(sellerIds),
                query.criteria("start").lessThanOrEq(now),
                query.criteria("end").greaterThanOrEq(now),
                query.criteria("confirm").equal(true),
                query.criteria("action").equal(LiveActionEnum.Available.getCode())
        );
        return query.project("lid", true).project("sid", true).disableValidation().asList();
    }

    /**
     * 根据直播Id查询直播信息
     * @param liveId
     * @return
     */
    public Live getLiveById(int liveId){
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class);
        query.field("lid").equal(liveId);

        return query.retrievedFields(true, liveFields).disableValidation().get(findOptionsLimitOne);
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

    /**
     * 获取商家历史直播信息
     * @param sellerId
     * @param limit
     * @return
     */
    public List<Live> getSellerHistoryLives(int sellerId, int limit){
        List<Live> lives = new ArrayList<>();
        Datastore datastore = getDatastore(dbName);
        Query<Live> query = datastore.find(Live.class).disableValidation()
                .retrievedFields(true, liveFields);
                //.retrievedFields(false, "_id");
        return query.field("sid").equal(sellerId)
                .field("action").equal(LiveActionEnum.Available.getCode())
                .field("end").lessThan(new Date())
                .field("confirm").equal(true)
                .limit(limit)
                .order("-lid")
                .asList();
    }
}















































