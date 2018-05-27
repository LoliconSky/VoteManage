package com.bfchengnuo.common.bean;

import com.bfchengnuo.po.Choose;

/**
 * 对选项的增强，增加了用户的选择项
 * Created by 冰封承諾Andy on 2018/5/27.
 */
public class ChooseAndSelect {
    private Choose choose;
    private boolean isSelect = false;

    public ChooseAndSelect() {
    }

    public ChooseAndSelect(Choose choose, boolean isSelect) {
        this.choose = choose;
        this.isSelect = isSelect;
    }

    public Choose getChoose() {
        return choose;
    }

    public void setChoose(Choose choose) {
        this.choose = choose;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
