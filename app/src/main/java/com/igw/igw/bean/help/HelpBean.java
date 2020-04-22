package com.igw.igw.bean.help;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 帮助相关
 *
 */
public class HelpBean {


    /**
     * code : 200
     * data : {"total":0,"rows":[{"id":1,"helpTitle":"支付相关问题","helpContent":"支付帮助内容","sort":1,"ctime":"2019-9-15 15:47:00"}]}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * total : 0
         * rows : [{"id":1,"helpTitle":"支付相关问题","helpContent":"支付帮助内容","sort":1,"ctime":"2019-9-15 15:47:00"}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * helpTitle : 支付相关问题
             * helpContent : 支付帮助内容
             * sort : 1
             * ctime : 2019-9-15 15:47:00
             */

            private int id;
            private String helpTitle;
            private String helpContent;
            private int sort;
            private String ctime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHelpTitle() {
                return helpTitle;
            }

            public void setHelpTitle(String helpTitle) {
                this.helpTitle = helpTitle;
            }

            public String getHelpContent() {
                return helpContent;
            }

            public void setHelpContent(String helpContent) {
                this.helpContent = helpContent;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
