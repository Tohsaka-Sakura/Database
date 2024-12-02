package com.hospital.service.impl;

import com.hospital.mapper.roomMapper;
import com.hospital.pojo.Room;
import com.hospital.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roomServiceImpl implements roomService {

    @Autowired
    private roomMapper roomMap;

    @Override
    public void addRoom(Room room) {
        roomMap.addRoom(room);
    }
}