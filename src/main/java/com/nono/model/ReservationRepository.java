/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nono.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author nono
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    public List<Reservation> findByUser(User user);
}
