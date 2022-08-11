/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung.ReturnMessage;
import model.CurrentUser;

/**
 *
 * @author Admin
 * @param <T>
 */
public interface ICommon<T> {
    ReturnMessage getData();
    ReturnMessage getById(String id);
    ReturnMessage setData(T entity);
    ReturnMessage removeData(String id);
}
