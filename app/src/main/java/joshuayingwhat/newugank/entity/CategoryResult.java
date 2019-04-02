package joshuayingwhat.newugank.entity;

import java.util.List;

import joshuayingwhat.newugank.network.response.BaseResponseEntity;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public class CategoryResult extends BaseResponseEntity {
//    public boolean error;

    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;
    }
}
