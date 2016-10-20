package com.example.dcrelling.guardian.framework;

/**
 * Created by davidcrelling on 10/17/16.
 */

public interface View<T, U>
{
    void setPresenter(T presenter);

    void setModel(U model);
}
