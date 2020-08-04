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
     * data : {"total":1,"rows":[{"ctime":"2020-05-19 11:56:09","helpContent":"<p>双语聊天城是收费功能，可以进行时时的在线沟通。<\/p><p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png\" style=\"width: 709px;\"><br><\/p>","helpEnContent":"<p>双语聊天城是收费功能，可以进行时时的在线沟通。<\/p><p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png\" style=\"width: 709px;\"><br><\/p>","helpEnTitle":"如何计费","helpTitle":"如何计费","id":1,"isDel":0,"sort":1,"sortWithOutOrderBy":"","sort_":""}]}
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
         * total : 1
         * rows : [{"ctime":"2020-05-19 11:56:09","helpContent":"<p>双语聊天城是收费功能，可以进行时时的在线沟通。<\/p><p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png\" style=\"width: 709px;\"><br><\/p>","helpEnContent":"<p>双语聊天城是收费功能，可以进行时时的在线沟通。<\/p><p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png\" style=\"width: 709px;\"><br><\/p>","helpEnTitle":"如何计费","helpTitle":"如何计费","id":1,"isDel":0,"sort":1,"sortWithOutOrderBy":"","sort_":""}]
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
             * ctime : 2020-05-19 11:56:09
             * helpContent : <p>双语聊天城是收费功能，可以进行时时的在线沟通。</p><p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png" style="width: 709px;"><br></p>
             * helpEnContent : <p>双语聊天城是收费功能，可以进行时时的在线沟通。</p><p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200730/20200730193957_657_common.png" style="width: 709px;"><br></p>
             * helpEnTitle : 如何计费
             * helpTitle : 如何计费
             * id : 1
             * isDel : 0
             * sort : 1
             * sortWithOutOrderBy :
             * sort_ :
             */

            private String ctime;
            private String helpContent;
            private String helpEnContent;
            private String helpEnTitle;
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

            public String getHelpEnContent() {
                return helpEnContent;
            }

            public void setHelpEnContent(String helpEnContent) {
                this.helpEnContent = helpEnContent;
            }

            public String getHelpEnTitle() {
                return helpEnTitle;
            }

            public void setHelpEnTitle(String helpEnTitle) {
                this.helpEnTitle = helpEnTitle;
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


//    /**
//     * code : 200
//     * data : {"total":0,"rows":[{"id":1,"helpTitle":"支付相关问题","helpContent":"支付帮助内容","sort":1,"ctime":"2019-9-15 15:47:00"}]}
//     * message : 操作成功
//     */
//
//    private int code;
//    private DataBean data;
//    private String message;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public static class DataBean {
//        /**
//         * total : 0
//         * rows : [{"id":1,"helpTitle":"支付相关问题","helpContent":"支付帮助内容","sort":1,"ctime":"2019-9-15 15:47:00"}]
//         */
//
//        private int total;
//        private List<RowsBean> rows;
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//
//        public List<RowsBean> getRows() {
//            return rows;
//        }
//
//        public void setRows(List<RowsBean> rows) {
//            this.rows = rows;
//        }
//
//        public static class RowsBean {
//            /**
//             * id : 1
//             * helpTitle : 支付相关问题
//             * helpContent : 支付帮助内容
//             * sort : 1
//             * ctime : 2019-9-15 15:47:00
//             */
//
//            private int id;
//            private String helpTitle;
//            private String helpContent;
//            private int sort;
//            private String ctime;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getHelpTitle() {
//                return helpTitle;
//            }
//
//            public void setHelpTitle(String helpTitle) {
//                this.helpTitle = helpTitle;
//            }
//
//            public String getHelpContent() {
//                return helpContent;
//            }
//
//            public void setHelpContent(String helpContent) {
//                this.helpContent = helpContent;
//            }
//
//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public String getCtime() {
//                return ctime;
//            }
//
//            public void setCtime(String ctime) {
//                this.ctime = ctime;
//            }
//        }
//    }
}
