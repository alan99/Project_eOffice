package com.alan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.LeaveMeetingForm;

public interface LeaveMeetingRepo extends JpaRepository<LeaveMeetingForm, Long> {

}