package builder;

/**
 * @Author: Jeremy
 * @Date: 2020/8/20 17:18
 */
public class QueryCondition {
    private String username;

    private Boolean useCategory;
    private String category;

    private Boolean fuzzy;
    private String keyword;

    private Integer pageSize;
    private Integer pageNo;

    public String getUsername() {
        return username;
    }

    public Boolean getUseCategory() {
        return useCategory;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getFuzzy() {
        return fuzzy;
    }

    public String getKeyword() {
        return keyword;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    private QueryCondition(QueryConditionBuilder builder) {
        this.username = builder.username;
        this.useCategory = builder.useCategory;
        this.category = builder.category;
        this.fuzzy = builder.fuzzy;
        this.keyword = builder.keyword;
        this.pageSize = builder.pageSize;
        this.pageNo = builder.pageNo;
    }

    @Override
    public String toString() {
        return String.format("%s is querying products, the query condition is [useCategory = %s," +
                "category = %s, " +
                "fuzzy = %s, " +
                "keyword = '%s', " +
                "pageSize = %d, " +
                "pageNo = %d]", username, useCategory, category, fuzzy, keyword, pageSize, pageNo);
    }

    public static class QueryConditionBuilder {
        private String username;

        private Boolean useCategory;
        private String category;

        private Boolean fuzzy;
        private String keyword;

        private Integer pageSize;
        private Integer pageNo;

        public QueryConditionBuilder username(String username) {
            this.username = username;
            return this;
        }

        public QueryConditionBuilder useCategory(Boolean useCategory) {
            this.useCategory = useCategory;
            return this;
        }

        public QueryConditionBuilder category(String category) {
            this.category = category;
            return this;
        }

        public QueryConditionBuilder fuzzy(Boolean fuzzy) {
            this.fuzzy = fuzzy;
            return this;
        }

        public QueryConditionBuilder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public QueryConditionBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public QueryConditionBuilder pageNo(Integer pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public QueryCondition build() {
            if (this.username == null || this.username.isEmpty()) {
                throw new IllegalArgumentException("username can not be empty");
            }

            if (this.useCategory) {
                if (this.category == null || this.category.isEmpty()) {
                    throw new IllegalArgumentException("category can not be empty when useCategory is true");
                }
            }

            if (this.fuzzy) {
                if (this.keyword != null && !this.keyword.isEmpty()) {
                    this.keyword = "% " + this.keyword + " %";
                }
                if (this.category != null && !this.category.isEmpty()) {
                    this.category = "%" + this.category + "%";
                }
            }

            return new QueryCondition(this);
        }
    }

}
