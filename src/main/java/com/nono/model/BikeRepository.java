/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nono.model;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author nono
 */
public interface BikeRepository extends CrudRepository<Bike, Long> {
    
}
