package com.igw.igw.bean.help;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class HelpInfoBean {


    /**
     * code : 200
     * data : {"ctime":"2020-05-19 11:56:09","helpContent":"<p>双语聊天城是收费功能，可以进行时时的在线沟通。<\/p><p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png\" style=\"width: 709px;\"><br><\/p>","helpTitle":"如何计费","id":1,"isDel":0,"sort":1,"sortWithOutOrderBy":"","sort_":""}
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
         * ctime : 2020-05-19 11:56:09
         * helpContent : <p>双语聊天城是收费功能，可以进行时时的在线沟通。</p><p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png" style="width: 709px;"><br></p>
         * helpTitle : 如何计费
         * id : 1
         * isDel : 0
         * sort : 1
         * sortWithOutOrderBy :
         * sort_ :
         */

        private String ctime;
        private String helpContent;
        private String helpTitle;
        private int id;
        private int isDel;
        private int sort;
        private String sortWithOutOrderBy;
        private String sort_;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getHelpContent() {
            return helpContent;
        }

        public void setHelpContent(String helpContent) {
            this.helpContent = helpContent;
        }

        public String getHelpTitle() {
            return helpTitle;
        }

        public void setHelpTitle(String helpTitle) {
            this.helpTitle = helpTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getSortWithOutOrderBy() {
            return sortWithOutOrderBy;
        }

        public void setSortWithOutOrderBy(String sortWithOutOrderBy) {
            this.sortWithOutOrderBy = sortWithOutOrderBy;
        }

        public String getSort_() {
            return sort_;
        }

        public void setSort_(String sort_) {
            this.sort_ = sort_;
        }
    }
}
