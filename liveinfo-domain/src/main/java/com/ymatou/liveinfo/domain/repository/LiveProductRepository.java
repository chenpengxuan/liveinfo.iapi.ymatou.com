package com.ymatou.liveinfo.domain.repository;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.ymatou.liveinfo.domain.model.LiveProduct;
import com.ymatou.liveinfo.infrastructure.mongodb.MongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gejianhua on 2017/4/10.
 */
@Component
public class LiveProductRepository extends MongoRepository {
    @Resource(name = "productMongoClient")
    private MongoClient mongoClient;

    private final String dbName = "YmtProducts";

    private final String[] liveProductFields = "spid,brand,tcatname,bid,tcatid,scatid".split(",");

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
     * 查询直播商品Id
     * @param liveId
     * @param limit
     * @return
     */
    public List<String> getProductIdsByLive(int liveId, int limit) {
        Date now = new Date();
        Datastore datastore = getDatastore(dbName);
        Query query = datastore.find(LiveProduct.class).disableValidation()
                .field("lid").equal(liveId)
                .field("status").equal(1)
                .field("start").lessThanOrEq(now)
                .field("end").greaterThanOrEq(now)
                .order("sort")
                .project("spid", true)
                .project("_id", false);
        if(limit > 0){
            query.limit(limit);
        }

        List<LiveProduct> liveProductList = query.asList();
        return liveProductList.stream().map((liveProduct)->{return liveProduct.getProductId();})
                .collect(Collectors.toList());
    }

    /**
     * 根据直播Id查找在售的商品
     * @param liveId
     * @return
     */
    public List<LiveProduct> getLiveProductsByLive(int liveId){
        Date now = new Date();
        Datastore datastore = getDatastore(dbName);
        Query query = datastore.find(LiveProduct.class).disableValidation()
                .field("lid").equal(liveId)
                .field("status").equal(1)
                .field("start").lessThanOrEq(now)
                .field("end").greaterThanOrEq(now)
                .order("sort")
                .retrievedFields(true, liveProductFields);

        List<LiveProduct> liveProductList = query.asList();

        return liveProductList;
    }

    /**
     * 获取直播品牌
     * @param liveId
     * @return
     */
    public List<LiveProduct> getBrandsOfLive(int liveId){
        Datastore datastore = getDatastore(dbName);
        Query query = datastore.find(LiveProduct.class).disableValidation()
                .field("lid").equal(liveId)
                .field("bid").greaterThan(0);

        Iterator<LiveProduct> liveProducts =
        datastore.createAggregation(LiveProduct.class).match(query)
                .group("bid",
                        Group.grouping("brand", Group.first("brand")),
                        Group.grouping("ebrand", Group.first("ebrand")))
                .project(Projection.projection("bid", "_id"),
                        Projection.projection("brand"),
                        Projection.projection("ebrand"),
                        Projection.projection("_id").suppress())
                .aggregate(LiveProduct.class);

        return Lists.newArrayList(liveProducts);
    }

    /**
     * 获取直播品类
     * @param liveId
     * @return
     */
    public List<LiveProduct> getCategoriesOfLive(int liveId){
        Datastore datastore = getDatastore(dbName);
        Query query = datastore.find(LiveProduct.class).disableValidation()
                .field("lid").equal(liveId)
                .field("scatid").greaterThan(0)
                .field("tcatid").greaterThan(0);

        List<Group> groups = new ArrayList<>();
        groups.add(Group.grouping("scatid"));
        groups.add(Group.grouping("tcatid"));

        Iterator<LiveProduct> liveProducts =
                datastore.createAggregation(LiveProduct.class).match(query)
                        .group(groups,
                                Group.grouping("scatname", Group.first("scatname")),
                                Group.grouping("tcatname", Group.first("tcatname")))
                        .project(Projection.projection("scatid", "_id.scatid"),
                                Projection.projection("tcatid", "_id.tcatid"),
                                Projection.projection("scatname"),
                                Projection.projection("tcatname"),
                                Projection.projection("_id").suppress())
                        .aggregate(LiveProduct.class);

        List<LiveProduct> liveProductList = Lists.newArrayList(liveProducts);
        return liveProductList;
    }
}





















































