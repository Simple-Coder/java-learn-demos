package com.learn.boot.validate;

import javax.validation.groups.Default;

/**
 * Created by dongxie on 2022/8/29.
 */
public interface ValidGroup extends Default {

    interface Crud extends ValidGroup {
        interface Create extends Crud {

        }

        interface Update extends Crud {

        }

        interface Query extends Crud {

        }

        interface Delete extends Crud {

        }
    }
}
