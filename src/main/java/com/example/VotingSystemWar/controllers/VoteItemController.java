package com.example.VotingSystemWar.controllers;

import antlr.StringUtils;
import com.example.VotingSystemWar.models.VoteItem;
import com.example.VotingSystemWar.models.VoteRecord;
import com.example.VotingSystemWar.repositories.VoteItemRepository;
import com.example.VotingSystemWar.repositories.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class VoteItemController {
    @Autowired
    private VoteItemRepository voteItemRepository;

    @Autowired
    private VoteRecordRepository voteRecordRepository;

    // 返回所有投票項目的名稱列表的HTML视图
    @GetMapping(value = "/vote-items")
    public String getAllVoteItemNames(Model model) {
        List<VoteItem> voteItems = voteItemRepository.findAll();
        List<String> itemNames = voteItems.stream()
                .map(VoteItem::getName)
                .collect(Collectors.toList());

        model.addAttribute("itemNames", itemNames);

        return "vote-item-list"; // 返回HTML（例如：vote-item-list.html）
    }

    // 處理POST請求以創建新的投票項目
    @PostMapping(value = "/vote-items")
    public String createVoteItem(@ModelAttribute VoteItem voteItem, RedirectAttributes redirectAttributes) {
        // 檢查投票項目是否有效，例如，檢查名稱是否為非空
        if (voteItem.getName() == null || voteItem.getName().trim().isEmpty()) {
            // 如果名稱為空，返回錯誤消息並重定向回創建投票項目的頁面
            redirectAttributes.addFlashAttribute("error", "投票項目名稱不能為空");
            return "redirect:/api/add-vote-item";
        }

        try {
            voteItemRepository.save(voteItem);
            // 假設成功創建了投票項目，然後重定向到投票項目列表頁面
            redirectAttributes.addFlashAttribute("success", "成功創建投票項目");
            return "redirect:/api/vote-items";
        } catch (Exception e) {
            // 如果發生異常，返回錯誤消息並重定向回創建投票項目的頁面
            redirectAttributes.addFlashAttribute("error", "創建投票項目時發生錯誤");
            return "redirect:/api/add-vote-item";
        }
    }

    @GetMapping(value = "/add-vote-item")
    public String showAddVoteItemForm() {
        return "vote-item-list"; // 返回HTML（例如：add-vote-item.html）
    }

    // 刪除投票項目
    @DeleteMapping(value = "/vote-items/{id}")
    public String deleteVoteItem(@PathVariable Long id) {
        VoteItem existingVoteItem = voteItemRepository.findById(id).orElse(null);
        if (existingVoteItem != null) {
            voteItemRepository.delete(existingVoteItem);
        }

        return "redirect:/api/vote-items"; // 重定向到投票项列表页
    }

    // 返回所有投票紀錄的投票人名單的HTML视图
    @GetMapping(value = "/vote-records")
    public String getAllVoters(Model model) {
        List<VoteRecord> voteRecords = voteRecordRepository.findAll();
        List<String> voters = voteRecords.stream()
                .map(VoteRecord::getVoter)
                .collect(Collectors.toList());

        model.addAttribute("voters", voters);

        return "vote-record-list"; // 返回HTML（例如：vote-record-list.html）
    }

    @GetMapping(value = "/add-vote-record")
    public String showAddVoteRecordForm() {
        return "add-vote-record"; // 返回HTML（例如：add-vote-record.html）
    }

    @PostMapping(value = "/add-vote-record")
    public String createVoteRecord(@ModelAttribute VoteRecord voteRecord) {
        voteRecordRepository.save(voteRecord);
        return "redirect:/api/vote-records";
    }
}
