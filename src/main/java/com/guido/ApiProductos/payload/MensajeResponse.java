/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.payload;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

import lombok.ToString;

/**
 *
 * @author Guido
 */
@Data
@Builder
@ToString
public class MensajeResponse implements Serializable {
    
    private String msj;
    private Object object;
}
