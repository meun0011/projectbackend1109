package com.it.rmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.rmu.entity.SlipImgEntity;

@Repository
public interface SlipImgRepository extends JpaRepository<SlipImgEntity, Integer> {

    @Query("select t from SlipImgEntity t where t.ordersId = ?1")
    public List<SlipImgEntity> findByOrdersId(Integer ordersId);

    @Modifying(clearAutomatically = true)
    @Query("delete from SlipImgEntity t where t.ordersId = ?1")
    void deleteByOrdersId(Integer ordersId);

    @Query("select t from SlipImgEntity t where t.slipImgName = ?1")
    public SlipImgEntity findBySlipImgName(String slipImgName);
}