package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;

import java.util.List;

public interface IItemService {

    ItemDto findById(Integer id);
    List<ItemDto> selectAll();
}