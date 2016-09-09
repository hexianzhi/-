package com.example.asus.xiaozhizhi_1.Model;

import java.util.List;

public  class ResultBean {
        private String stat;
       
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

      
    }