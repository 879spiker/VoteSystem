package com.example.VotingSystemWar.repositories;

import com.example.VotingSystemWar.models.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
    // 定義自定義的查詢方法（如果需要）
}
